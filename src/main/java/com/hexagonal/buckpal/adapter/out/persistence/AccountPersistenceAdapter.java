package com.hexagonal.buckpal.adapter.out.persistence;

import com.hexagonal.buckpal.application.domain.model.Account;
import com.hexagonal.buckpal.application.port.out.LoadAccountPort;
import com.hexagonal.buckpal.application.port.out.UpdateAccountStatePort;
import com.hexagonal.buckpal.common.PersistenceAdapter;

import java.time.LocalDateTime;

@PersistenceAdapter
public class AccountPersistenceAdapter implements
        LoadAccountPort,
        UpdateAccountStatePort {

    @Override
    public Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate) {
        return null;
    }

    @Override
    public void updateActivities(Account account) {

    }
}
