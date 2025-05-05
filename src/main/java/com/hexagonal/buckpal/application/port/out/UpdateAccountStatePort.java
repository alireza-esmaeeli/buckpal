package com.hexagonal.buckpal.application.port.out;

import com.hexagonal.buckpal.application.domain.model.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);
}
