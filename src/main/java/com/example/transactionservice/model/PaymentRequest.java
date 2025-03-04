package com.example.transactionservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment_requests")
public class PaymentRequest {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "uid", nullable = false)
    private UUID id;

    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @Column(name = "user_uid", nullable = false)
    private UUID userUid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_uid", nullable = false)
    private Wallet walletUid;

    @ColumnDefault("0.0")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "comment", length = 256)
    private String comment;

    @Column(name = "payment_method_id")
    private Long paymentMethodId;

}