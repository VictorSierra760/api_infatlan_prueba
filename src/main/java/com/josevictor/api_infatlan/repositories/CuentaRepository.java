package com.josevictor.api_infatlan.repositories;

import com.josevictor.api_infatlan.entities.accounts;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<accounts, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<accounts> findWithLockById(Long id);
}
