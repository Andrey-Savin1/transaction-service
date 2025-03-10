package com.example.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private UUID userUid;
    private UUID walletUid;
    private BigDecimal amount;
    private String comment;
    private String currency;
}
