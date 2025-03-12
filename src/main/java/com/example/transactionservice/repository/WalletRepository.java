package com.example.transactionservice.repository;

import com.example.transactionservice.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, Long> {


    Optional<Wallet> findByNameAndUserUid(String id, Long userId);

    Optional<Wallet> findByUserUid(Long userId);
}
