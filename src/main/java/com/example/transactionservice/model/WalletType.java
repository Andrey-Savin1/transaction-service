package com.example.transactionservice.model;

import com.example.transactionservice.model.enums.CurrencyCode;
import com.example.transactionservice.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "wallet_types")
public class WalletType {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "uid", nullable = false)
    private UUID id = UUID.randomUUID();

    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "currency_code", nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "status", nullable = false, length = 18)
    private String status;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    @Column(name = "user_type", length = 15)
    @Enumerated(EnumType.STRING)
    private UserType userType ;

    @Column(name = "creator")
    private String creator;

    @Column(name = "modifier")
    private String modifier;

}