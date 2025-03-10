package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.WithdrawalRequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.model.WithdrawalRequest;
import com.example.transactionservice.model.enums.FilterType;
import com.example.transactionservice.model.enums.TransactionState;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.repository.WithdrawalRequestRepository;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.WithdrawalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WithdrawalRequestServiceImpl implements WithdrawalRequestService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WithdrawalRequestRepository withdrawalRequestRepository;
    @Autowired
    private PaymentRequestService paymentRequestService;


    public WithdrawalRequest createWithdrawalRequest(WithdrawalRequestDto dto) {
        PaymentRequest paymentRequest = paymentRequestService.getPaymentRequest(dto);

        if (paymentRequest == null) {
            throw new RuntimeException("PaymentRequest is null");
        }

        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setPaymentRequestUid(paymentRequest);

        Transaction transaction = new Transaction();
        transaction.setUserUid(paymentRequest.getUserUid());
        transaction.setWalletUid(paymentRequest.getWallet());
        transaction.setWalletName(paymentRequest.getWallet().getName());
        transaction.setAmount(dto.getAmount());
        transaction.setType(FilterType.WITHDRAWAL);
        transaction.setState(TransactionState.PROCESSING);
        transaction.setPaymentRequestUid(paymentRequest);
        transactionRepository.save(transaction);

        return withdrawalRequestRepository.save(withdrawalRequest);

    }
}
