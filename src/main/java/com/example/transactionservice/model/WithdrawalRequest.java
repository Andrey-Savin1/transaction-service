package com.example.transactionservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "withdrawal_requests")
public class WithdrawalRequest {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "uid", nullable = false)
    private UUID id = UUID.randomUUID();

    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_request_uid", nullable = false)
    private PaymentRequest paymentRequestUid;

}