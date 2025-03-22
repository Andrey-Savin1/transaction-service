package com.example.transactionservice.repository;

import com.example.transactionservice.model.Wallet;
import com.example.transactionservice.model.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByNameAndUserUid(String id, Long userId);

    Optional<Wallet> findByUserUid(Long userId);

    List<Wallet> findAllByUserUid(Long userId);

    List<Wallet> findAllByUserUidAndWalletType(Long userId, WalletType walletType);
}
