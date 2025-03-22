package com.example.transactionservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDto {

    @NotNull(message = "Source wallet UID cannot be null")
    private Long walletUidFrom;

    @NotNull(message = "Target wallet UID cannot be null")
    private Long walletUidTo;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    private String comment;

    @NotBlank(message = "Currency code cannot be blank")
    private String currencyCode;

    @NotBlank(message = "Target currency code cannot be blank")
    private String targetCurrencyCode;
}
