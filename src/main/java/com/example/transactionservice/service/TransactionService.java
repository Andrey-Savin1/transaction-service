package com.example.transactionservice.service;

import java.util.UUID;

public interface TransactionService {

   void processTopUpTransaction(UUID paymentRequestUid);

}
