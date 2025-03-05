package com.example.transactionservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TopUpRequestDto {

    private UUID userUid;
    private UUID walletUid;
    private BigDecimal amount;
    private String comment;
    private String provider;

}
