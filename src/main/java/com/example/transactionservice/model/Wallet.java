package com.example.transactionservice.model;

import com.example.transactionservice.model.enums.WalletStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "wallets")
public class Wallet {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "uid")
    private String id = String.valueOf(UUID.randomUUID());

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_type_uid")
    private WalletType walletType;

    @Column(name = "user_uid")
    private UUID userUid = UUID.randomUUID();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WalletStatus status;

    @ColumnDefault("0.0")
    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "archived_at")
    private LocalDate archivedAt;

}