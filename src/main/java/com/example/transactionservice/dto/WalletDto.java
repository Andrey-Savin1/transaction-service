package com.example.transactionservice.dto;


import com.example.transactionservice.model.WalletType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class WalletDto {

    private WalletTypeDto walletTypeDto;
    private String name;
    private UUID userUid;

}
