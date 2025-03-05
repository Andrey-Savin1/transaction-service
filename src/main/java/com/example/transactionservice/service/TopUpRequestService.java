package com.example.transactionservice.service;

import com.example.transactionservice.model.TopUpRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface TopUpRequestService  {


    TopUpRequest createTopUpRequest(UUID userUid, UUID walletUid, BigDecimal amount, String comment, String provider);

}
