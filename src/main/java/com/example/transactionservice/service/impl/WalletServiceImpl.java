package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.WalletDto;
import com.example.transactionservice.dto.WalletUpdateDto;
import com.example.transactionservice.model.Wallet;
import com.example.transactionservice.model.enums.WalletStatus;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.service.WalletService;
import com.example.transactionservice.service.WalletTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTypeService walletTypeService;


    @Override
    public Wallet createWallet(WalletDto dto) {

//        var findWallet = walletRepository.findByNameAndUserUid(dto.getName(), dto.getUserUid());
//        if (findWallet.isPresent()) {
//            return findWallet.get();
//        } else {
            Wallet newWallet = new Wallet();
            newWallet.setName(dto.getName());
            newWallet.setWalletType(walletTypeService.getNeedWalletType(dto.getWalletTypeDto()));
            newWallet.setStatus(WalletStatus.ACTIVE);
            newWallet.setBalance(BigDecimal.ZERO);
            return walletRepository.save(newWallet);

//        }
    }

    public Wallet updateWallet(WalletUpdateDto dto) {

        var findWallet = walletRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (dto.getName() != null) {
            findWallet.setName(dto.getName());
        }
        return walletRepository.save(findWallet);

    }

}
