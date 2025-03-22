package com.example.transactionservice.dto;

import com.example.transactionservice.model.enums.TransactionState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionStateDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private TransactionState state;
}
