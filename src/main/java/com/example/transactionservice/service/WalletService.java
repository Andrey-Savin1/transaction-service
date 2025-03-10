package com.example.transactionservice.service;

import com.example.transactionservice.dto.WalletDto;
import com.example.transactionservice.dto.WalletUpdateDto;
import com.example.transactionservice.model.Wallet;
import org.springframework.transaction.annotation.Transactional;

public interface WalletService {

    @Transactional
    Wallet createWallet(WalletDto dto);

    Wallet updateWallet(WalletUpdateDto dto);

}
