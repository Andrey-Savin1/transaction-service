package com.example.transactionservice.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletDto {

    private WalletTypeDto walletTypeDto;
    private String name;
    //private UUID userUid;

}
