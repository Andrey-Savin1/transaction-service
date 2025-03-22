package com.example.transactionservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transfer_requests")
public class TransferRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "system_rate")
    private String systemRate;

    @JoinColumn(name = "payment_request_uid_from")
    private Long paymentRequestUidFrom;

    @JoinColumn(name = "payment_request_uid_to")
    private Long paymentRequestUidTo;

}