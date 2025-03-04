package com.example.transactionservice.repository;

import com.example.transactionservice.model.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletTypeRepository extends JpaRepository<WalletType, UUID> {
}
