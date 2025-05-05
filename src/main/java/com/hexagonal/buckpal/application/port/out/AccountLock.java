package com.hexagonal.buckpal.application.port.out;

import com.hexagonal.buckpal.application.domain.model.Account;

public interface AccountLock {

    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);
}
