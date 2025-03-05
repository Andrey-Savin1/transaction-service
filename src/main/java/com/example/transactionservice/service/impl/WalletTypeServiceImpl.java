package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.WalletTypeDto;
import com.example.transactionservice.model.WalletType;
import com.example.transactionservice.repository.WalletTypeRepository;
import com.example.transactionservice.service.WalletTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletTypeServiceImpl implements WalletTypeService {

    @Autowired
    WalletTypeRepository walletTypeRepository;

    @Override
    public WalletType getNeedWalletType(WalletTypeDto dto) {

        WalletType walletType = new WalletType();
        walletType.setName(dto.getName());
        walletType.setCurrencyCode(dto.getCurrencyCode());
        walletType.setStatus("ACTIVE");
        walletType.setUserType(dto.getUserType());
        walletType.setCreator("USER");

        return walletTypeRepository.save(walletType);
    }

}
