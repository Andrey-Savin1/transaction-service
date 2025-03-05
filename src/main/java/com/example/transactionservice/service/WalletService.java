package com.example.transactionservice.service;

import com.example.transactionservice.dto.WalletDto;
import com.example.transactionservice.dto.WalletUpdateDto;
import com.example.transactionservice.model.Wallet;

public interface WalletService {

    Wallet createWallet(WalletDto dto);

    Wallet updateWallet(WalletUpdateDto dto);

}
