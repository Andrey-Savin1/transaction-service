package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.WalletTypeDto;
import com.example.transactionservice.model.WalletType;
import com.example.transactionservice.service.WalletTypeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletTypeServiceImpl implements WalletTypeService {



    @Override
    public WalletType getNeedWalletType(WalletTypeDto dto) {

        WalletType walletType = new WalletType();
        walletType.setCreatedAt(LocalDateTime.now());
        walletType.setName(dto.getName());
        walletType.setCurrencyCode(dto.getCurrencyCode());
        walletType.setStatus("ACTIVE");
        walletType.setUserType(dto.getUserType());
        walletType.setCreator("USER");
        return walletType;
    }





}
