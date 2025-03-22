package com.example.transactionservice.service;

import com.example.transactionservice.dto.RequestDto;
import com.example.transactionservice.model.PaymentRequest;

import java.math.BigDecimal;

public interface PaymentRequestService {

    PaymentRequest createPaymentRequest(Long userUid, BigDecimal amount, String comment, String currency);

    <T extends RequestDto> PaymentRequest getPaymentRequest(T dto);

}
