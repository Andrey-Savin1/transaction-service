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
@Table(name = "top_up_requests")
public class TopUpRequest {
    @Id
    @Column(name = "uid")
    private UUID id = UUID.randomUUID();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "provider")
    private String provider;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_request_uid")
    private PaymentRequest paymentRequestUid;

}