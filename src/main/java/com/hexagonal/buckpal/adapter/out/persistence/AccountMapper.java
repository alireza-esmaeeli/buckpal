package com.hexagonal.buckpal.adapter.out.persistence;

import com.hexagonal.buckpal.application.domain.model.Account;
import com.hexagonal.buckpal.application.domain.model.Activity;
import com.hexagonal.buckpal.application.domain.model.ActivityWindow;
import com.hexagonal.buckpal.application.domain.model.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.hexagonal.buckpal.application.domain.model.Account.AccountId;
import static com.hexagonal.buckpal.application.domain.model.Account.withId;

@Component
class AccountMapper {

    Account mapToDomainEntity(AccountJpaEntity account,
                              List<ActivityJpaEntity> activities,
                              Long withdrawalBalance,
                              Long depositBalance) {

        Money baselineBalance = Money.subtract(
                Money.of(depositBalance),
                Money.of(withdrawalBalance));

        return withId(
                new AccountId(account.getId()),
                baselineBalance,
                mapToActivityWindow(activities));
    }

    private ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();

        activities.forEach(jpaEntity ->
                mappedActivities.add(new Activity(
                        new Activity.ActivityId(jpaEntity.getId()),
                        new AccountId(jpaEntity.getOwnerAccountId()),
                        new AccountId(jpaEntity.getSourceAccountId()),
                        new AccountId(jpaEntity.getTargetAccountId()),
                        LocalDateTime.ofInstant(jpaEntity.getTimestamp().toInstant(), ZoneId.systemDefault()),
                        Money.of(jpaEntity.getAmount())
                )));

        return new ActivityWindow(mappedActivities);
    }

    ActivityJpaEntity mapToJpaEntity(Activity activity) {
        return new ActivityJpaEntity(
                activity.id() == null ? null : activity.id().value(),
                activity.timestamp().atZone(ZoneId.systemDefault()).toOffsetDateTime(),
                activity.ownerAccountId().value(),
                activity.sourceAccountId().value(),
                activity.targetAccountId().value(),
                activity.money().amount().longValue());
    }
}
