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
    @Column(name = "uid")
    private String id = String.valueOf(UUID.randomUUID());

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "name")
    private String name;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "status")
    private String status;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "creator")
    private String creator;

    @Column(name = "modifier")
    private String modifier;

}