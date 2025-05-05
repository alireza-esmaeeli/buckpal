package com.hexagonal.buckpal.adapter.out.persistence;

import com.hexagonal.buckpal.application.domain.model.Account;
import com.hexagonal.buckpal.application.domain.model.Activity;
import com.hexagonal.buckpal.application.port.out.LoadAccountPort;
import com.hexagonal.buckpal.application.port.out.UpdateAccountStatePort;
import com.hexagonal.buckpal.common.PersistenceAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.hexagonal.buckpal.application.domain.model.Account.AccountId;

@RequiredArgsConstructor
@PersistenceAdapter
class AccountPersistenceAdapter implements
        LoadAccountPort,
        UpdateAccountStatePort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(AccountId accountId, LocalDateTime baselineDate) {

        AccountJpaEntity account =
                accountRepository.findById(accountId.value())
                        .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities =
                activityRepository.findByOwnerSince(
                        accountId.value(),
                        baselineDate.atZone(ZoneId.systemDefault()).toOffsetDateTime());

        Long withdrawalBalance = activityRepository
                .getWithdrawalBalanceUntil(
                        accountId.value(),
                        baselineDate.atZone(ZoneId.systemDefault()).toOffsetDateTime())
                .orElse(0L);

        Long depositBalance = activityRepository
                .getDepositBalanceUntil(
                        accountId.value(),
                        baselineDate.atZone(ZoneId.systemDefault()).toOffsetDateTime())
                .orElse(0L);

        return accountMapper.mapToDomainEntity(
                account,
                activities,
                withdrawalBalance,
                depositBalance);
    }

    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.id() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }
}
