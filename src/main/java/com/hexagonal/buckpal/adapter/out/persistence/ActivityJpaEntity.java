package com.hexagonal.buckpal.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
class ActivityJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private OffsetDateTime timestamp;

    @Column
    private long ownerAccountId;

    @Column
    private long sourceAccountId;

    @Column
    private long targetAccountId;

    @Column
    private long amount;
}
