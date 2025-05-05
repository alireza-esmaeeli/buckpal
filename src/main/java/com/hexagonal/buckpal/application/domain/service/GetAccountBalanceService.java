package com.hexagonal.buckpal.application.domain.service;

import com.hexagonal.buckpal.application.domain.model.Money;
import com.hexagonal.buckpal.application.port.in.GetAccountBalanceUseCase;
import com.hexagonal.buckpal.application.port.out.LoadAccountPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceUseCase {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(GetAccountBalanceQuery query) {
        return loadAccountPort.loadAccount(query.accountId(), LocalDateTime.now())
                .calculateBalance();
    }
}
