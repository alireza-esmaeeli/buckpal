package com.hexagonal.buckpal.adapter.out.persistence;

import com.hexagonal.buckpal.application.domain.model.Account;
import com.hexagonal.buckpal.application.port.out.AccountLock;
import org.springframework.stereotype.Component;

@Component
class NoOpAccountLock implements AccountLock {
    @Override
    public void lockAccount(Account.AccountId accountId) {

    }

    @Override
    public void releaseAccount(Account.AccountId accountId) {

    }
}
