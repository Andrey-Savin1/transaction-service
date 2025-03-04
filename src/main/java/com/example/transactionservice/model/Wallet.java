package com.example.transactionservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "uid", nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "wallet_type_uid", nullable = false)
    private WalletType walletType;

    @Column(name = "user_uid", nullable = false)
    private UUID userUid;

    @Column(name = "status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private WalletStatus status;

    @ColumnDefault("0.0")
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "archived_at")
    private LocalDate archivedAt;

}