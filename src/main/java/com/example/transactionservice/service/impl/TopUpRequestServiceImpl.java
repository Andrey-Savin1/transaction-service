package com.example.transactionservice.service.impl;

import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.TopUpRequest;
import com.example.transactionservice.repository.TopUpRequestRepository;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.TopUpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TopUpRequestServiceImpl implements TopUpRequestService {

    @Autowired
    private PaymentRequestService paymentRequestService;
    @Autowired
    private TopUpRequestRepository topUpRequestRepository;


    public TopUpRequest createTopUpRequest(UUID userUid, UUID walletUid, BigDecimal amount, String comment, String provider) {
        PaymentRequest paymentRequest = paymentRequestService.createPaymentRequest(userUid, walletUid, amount, comment);
        TopUpRequest topUpRequest = new TopUpRequest();
        topUpRequest.setProvider(provider);
        topUpRequest.setPaymentRequestUid(paymentRequest);
        return topUpRequestRepository.save(topUpRequest);
    }

}
