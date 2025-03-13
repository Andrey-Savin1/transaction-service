package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.TopUpRequest;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.model.enums.FilterType;
import com.example.transactionservice.model.enums.TransactionState;
import com.example.transactionservice.repository.TopUpRequestRepository;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.TopUpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopUpRequestServiceImpl implements TopUpRequestService {

    @Autowired
    private PaymentRequestService paymentRequestService;
    @Autowired
    private TopUpRequestRepository topUpRequestRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public TopUpRequest createTopUpRequest(TopUpRequestDto dto) {
        PaymentRequest paymentRequest = paymentRequestService.getPaymentRequest(dto);

        if (paymentRequest == null) {
            throw new RuntimeException("PaymentRequest is null");
        }

        System.out.println("paymentRequest: " + paymentRequest.getUserUid());

        TopUpRequest topUpRequest = new TopUpRequest();
        topUpRequest.setProvider(dto.getProvider());
        topUpRequest.setPaymentRequestUid(paymentRequest);
        //topUpRequestRepository.save(topUpRequest);


        Transaction transaction = new Transaction();
        transaction.setUserUid(paymentRequest.getUserUid());
        transaction.setWalletUid(paymentRequest.getWallet());
        transaction.setWalletName(paymentRequest.getWallet().getName());
        transaction.setAmount(dto.getAmount());
        transaction.setType(FilterType.TOPUP);
        transaction.setState(TransactionState.PROCESSING);
        transaction.setPaymentRequestUid(paymentRequest);
        transactionRepository.save(transaction);

        return topUpRequestRepository.save(topUpRequest);

    }

}
