package com.example.transactionservice.repository;

import com.example.transactionservice.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, UUID> {
}
