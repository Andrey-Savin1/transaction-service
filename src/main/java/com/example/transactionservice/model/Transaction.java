package com.example.transactionservice.model;

import com.example.transactionservice.model.enums.FilterType;
import com.example.transactionservice.model.enums.TransactionState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "user_uid")
    private Long userUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_uid")
    private Wallet walletUid;

    @Column(name = "wallet_name")
    private String walletName;

    @ColumnDefault("0.0")
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FilterType type;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private TransactionState state;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "payment_request_uid")
    private PaymentRequest paymentRequestUid;

}