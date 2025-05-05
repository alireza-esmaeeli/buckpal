package com.hexagonal.buckpal.application.port.in;

import com.hexagonal.buckpal.application.domain.model.Money;

import static com.hexagonal.buckpal.application.domain.model.Account.AccountId;

public interface GetAccountBalanceUseCase {

    Money getAccountBalance(GetAccountBalanceQuery query);

    record GetAccountBalanceQuery(AccountId accountId) {
    }
}
