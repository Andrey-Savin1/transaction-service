package com.example.transactionservice.service.impl;

import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.enums.PaymentStatus;
import com.example.transactionservice.repository.PaymentRequestRepository;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.service.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentRequestServiceImpl implements PaymentRequestService {


    @Autowired
    private PaymentRequestRepository paymentRequestRepository;
    @Autowired
    private WalletRepository walletRepository;


    public PaymentRequest createPaymentRequest(UUID userUid, UUID walletUid, BigDecimal amount, String comment) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserUid(userUid);
        paymentRequest.setWallet(walletRepository.findById(walletUid).orElseThrow());
        paymentRequest.setAmount(amount);
        paymentRequest.setStatus(PaymentStatus.PROCESSING);
        paymentRequest.setComment(comment);
        return paymentRequestRepository.save(paymentRequest);
    }









}
