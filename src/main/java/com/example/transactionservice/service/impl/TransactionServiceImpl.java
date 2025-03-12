package com.example.transactionservice.service.impl;

import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.model.Wallet;
import com.example.transactionservice.model.enums.FilterType;
import com.example.transactionservice.model.enums.PaymentStatus;
import com.example.transactionservice.model.enums.TransactionState;
import com.example.transactionservice.repository.PaymentRequestRepository;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.UUID;


@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PaymentRequestRepository paymentRequestRepository;
    @Autowired
    private WalletRepository walletRepository;

    public void processTopUpTransaction(Long paymentRequestUid) {
        PaymentRequest paymentRequest = paymentRequestRepository.findById(paymentRequestUid)
                .orElseThrow(() -> new RuntimeException("Payment request not found"));

        if (!PaymentStatus.COMPLETED.equals(paymentRequest.getStatus())) {
            throw new RuntimeException("Payment request is not completed");
        }

        Wallet wallet = paymentRequest.getWallet();
        BigDecimal amount = paymentRequest.getAmount();

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setUserUid(paymentRequest.getUserUid());
        transaction.setWalletUid(wallet);
        transaction.setWalletName(wallet.getName());
        transaction.setAmount(amount);
        transaction.setType(FilterType.TOPUP);
        transaction.setState(TransactionState.COMPLETED);
        transaction.setPaymentRequestUid(paymentRequest);
        transactionRepository.save(transaction);

    }


}
