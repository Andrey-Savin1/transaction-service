package com.example.transactionservice.service;

import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.dto.TransactionStateDto;
import com.example.transactionservice.dto.TransferRequestDto;
import com.example.transactionservice.dto.WithdrawalRequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.Transaction;

public interface TransactionService {

    void processTopUpTransaction(Long paymentRequestUid);

    Transaction createTopUpTransaction(TopUpRequestDto dto, PaymentRequest paymentRequest);

    Transaction createWithdrawalTransaction(WithdrawalRequestDto dto, PaymentRequest paymentRequest);

    Transaction createTransferTransaction(TransferRequestDto dto, PaymentRequest paymentRequest);

    TransactionStateDto getTransactionById(Long transactionId);


}
