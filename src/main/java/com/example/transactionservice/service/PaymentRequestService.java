package com.example.transactionservice.service;

import com.example.transactionservice.model.PaymentRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentRequestService {

    PaymentRequest createPaymentRequest(UUID userUid, UUID walletUid, BigDecimal amount, String comment);



}
