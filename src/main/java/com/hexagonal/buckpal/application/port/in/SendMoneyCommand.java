package com.hexagonal.buckpal.application.port.in;

import com.hexagonal.buckpal.application.domain.model.Money;
import jakarta.validation.constraints.NotNull;

import static com.hexagonal.buckpal.application.domain.model.Account.AccountId;
import static com.hexagonal.buckpal.common.validation.Validation.validate;

public record SendMoneyCommand(
        @NotNull AccountId sourceAccountId,
        @NotNull AccountId targetAccountId,
        @NotNull @PositiveMoney Money money) {

    public SendMoneyCommand(AccountId sourceAccountId,
                            AccountId targetAccountId,
                            Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        validate(this);
    }
}
