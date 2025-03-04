package com.example.transactionservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WalletUpdateDto {

    private UUID id;
   // private WalletTypeDto walletTypeDto;
    private String name;
   // private UUID userUid;


}
