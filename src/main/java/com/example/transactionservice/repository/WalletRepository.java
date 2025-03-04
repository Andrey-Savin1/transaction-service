package com.example.transactionservice.repository;

import com.example.transactionservice.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {


    Optional<Wallet> findByNameAndUserUid(String id, UUID userId);
}
