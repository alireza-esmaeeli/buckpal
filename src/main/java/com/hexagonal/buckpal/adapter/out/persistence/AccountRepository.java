package com.hexagonal.buckpal.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {
}
