package com.example.transactionservice.service;

import com.example.transactionservice.dto.WalletTypeDto;
import com.example.transactionservice.model.WalletType;
import org.springframework.stereotype.Service;

@Service
public interface WalletTypeService {


    WalletType getNeedWalletType(WalletTypeDto dto);

}
