package com.hexagonal.buckpal.application.port.out;

import com.hexagonal.buckpal.application.domain.model.Account;

import java.time.LocalDateTime;

import static com.hexagonal.buckpal.application.domain.model.Account.AccountId;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
