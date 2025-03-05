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
@Table(name = "transfer_requests")
public class TransferRequest {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "uid", nullable = false)
    private UUID id = UUID.randomUUID();

    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "system_rate", nullable = false, length = Integer.MAX_VALUE)
    private String systemRate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_request_uid_from", nullable = false)
    private PaymentRequest paymentRequestUidFrom;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_request_uid_to", nullable = false)
    private PaymentRequest paymentRequestUidTo;

}