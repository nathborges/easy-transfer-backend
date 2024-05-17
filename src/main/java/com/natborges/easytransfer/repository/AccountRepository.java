package com.natborges.easytransfer.repository;

import com.natborges.easytransfer.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByCpf(String cpf);
    Optional<AccountEntity> findByAccountNumber(String number);
}