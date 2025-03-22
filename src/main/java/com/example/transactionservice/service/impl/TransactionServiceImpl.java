package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.dto.TransactionStateDto;
import com.example.transactionservice.dto.TransferRequestDto;
import com.example.transactionservice.dto.WithdrawalRequestDto;
import com.example.transactionservice.mappers.TransactionServiceMappers;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;


@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentRequestRepository paymentRequestRepository;
    private final WalletRepository walletRepository;
    private final TransactionServiceMappers mappers;


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
        transaction.setType(FilterType.TOP_UP);
        transaction.setState(TransactionState.COMPLETED);
        transaction.setPaymentRequestUid(paymentRequest);
        transactionRepository.save(transaction);

    }

    @Transactional
    public Transaction createTopUpTransaction(TopUpRequestDto dto, PaymentRequest paymentRequest) {

        Transaction transaction = new Transaction();
        transaction.setUserUid(paymentRequest.getUserUid());
        transaction.setWalletUid(paymentRequest.getWallet());
        transaction.setWalletName(paymentRequest.getWallet().getName());
        transaction.setAmount(dto.getAmount());
        transaction.setType(FilterType.TOP_UP);
        transaction.setState(TransactionState.PROCESSING);
        transaction.setPaymentRequestUid(paymentRequest);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Transactional
    public Transaction createWithdrawalTransaction(WithdrawalRequestDto dto, PaymentRequest paymentRequest) {

        Transaction transaction = new Transaction();
        transaction.setUserUid(paymentRequest.getUserUid());
        transaction.setWalletUid(paymentRequest.getWallet());
        transaction.setWalletName(paymentRequest.getWallet().getName());
        transaction.setAmount(dto.getAmount());
        transaction.setType(FilterType.TOP_UP);
        transaction.setState(TransactionState.PROCESSING);
        transaction.setPaymentRequestUid(paymentRequest);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Transactional
    public Transaction createTransferTransaction(TransferRequestDto dto, PaymentRequest paymentRequest) {

        Transaction transaction = new Transaction();
        transaction.setUserUid(paymentRequest.getUserUid());
        transaction.setWalletUid(paymentRequest.getWallet());
        transaction.setWalletName(paymentRequest.getWallet().getName());
        transaction.setAmount(dto.getAmount().negate());
        transaction.setType(FilterType.TRANSFER);
        transaction.setState(TransactionState.PROCESSING);
        transaction.setPaymentRequestUid(paymentRequest);
        transactionRepository.save(transaction);
        return transaction;
    }

    public TransactionStateDto getTransactionById(Long transactionId) {

        var transaction = transactionRepository.findById(transactionId).orElseThrow(()
                -> new RuntimeException("Транзакция не найдена"));
        return mappers.map(transaction);
    }

    public static Long determineShardValue(Long userId) {
        // Логика определения значения для шардирования
        return userId % 2;
    }

}
