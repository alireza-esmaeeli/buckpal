package com.hexagonal.buckpal.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    private AccountId id;
    @Getter
    private Money baselineBalance;
    @Getter
    private ActivityWindow activityWindow;

    public static Account withoutId(Money baselineBalance,
                                    ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public static Account withId(AccountId accountId,
                                 Money baselineBalance,
                                 ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Optional<AccountId> getId() {
        return Optional.ofNullable(this.id);
    }

    public Money calculateBalance() {
        return Money.add(
                this.baselineBalance,
                this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {

        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(
                this.id,
                this.id,
                targetAccountId,
                LocalDateTime.now(),
                money);

        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(
                        this.calculateBalance(),
                        money.negate())
                .isPositiveOrZero();
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = new Activity(
                this.id,
                sourceAccountId,
                this.id,
                LocalDateTime.now(),
                money);

        this.activityWindow.addActivity(deposit);
        return true;
    }

    public record AccountId(@NonNull Long value) {
    }
}
