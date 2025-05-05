package com.hexagonal.buckpal.application.domain.model;

import lombok.NonNull;

import java.time.LocalDateTime;

public record Activity(ActivityId id,
                       Account.AccountId ownerAccountId,
                       Account.AccountId sourceAccountId,
                       Account.AccountId targetAccountId,
                       LocalDateTime timestamp,
                       Money money) {

    public Activity(@NonNull Account.AccountId ownerAccountId,
                    @NonNull Account.AccountId sourceAccountId,
                    @NonNull Account.AccountId targetAccountId,
                    @NonNull LocalDateTime timestamp,
                    @NonNull Money money) {
        this(null, ownerAccountId, sourceAccountId, targetAccountId, timestamp, money);
    }

    public record ActivityId(Long value) {
    }
}
