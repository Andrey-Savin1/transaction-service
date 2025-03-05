package com.example.transactionservice.dto;


import com.example.transactionservice.model.enums.CurrencyCode;
import com.example.transactionservice.model.enums.UserType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletTypeDto {

    private String name;
    private CurrencyCode currencyCode;
    private UserType userType;


}
