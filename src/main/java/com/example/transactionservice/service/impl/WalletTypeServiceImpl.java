package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.WalletTypeDto;
import com.example.transactionservice.model.WalletType;
import com.example.transactionservice.repository.WalletTypeRepository;
import com.example.transactionservice.service.WalletTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class WalletTypeServiceImpl implements WalletTypeService {

    @Autowired
    WalletTypeRepository walletTypeRepository;

    @Override
    @Transactional
    public WalletType getNeedWalletType(WalletTypeDto dto) {

        WalletType walletType = new WalletType();
        walletType.setId(Math.abs(new Random().nextLong()));
        walletType.setName(dto.getName());
        walletType.setCurrencyCode(dto.getCurrencyCode());
        walletType.setStatus("ACTIVE");
        walletType.setUserType(dto.getUserType());
        walletType.setCreator("USER");

        return walletType;
    }

}
