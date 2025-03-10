package com.example.transactionservice.controller;


import com.example.transactionservice.dto.WalletDto;
import com.example.transactionservice.dto.WalletUpdateDto;
import com.example.transactionservice.model.Wallet;
import com.example.transactionservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/create")
    @Transactional
    public Wallet createWallet(@RequestBody WalletDto walletDto) {
        return walletService.createWallet(walletDto);
    }

    @PatchMapping("/update")
    public Wallet updateWallet(@RequestBody WalletUpdateDto walletDto) {
        return walletService.updateWallet(walletDto);
    }

}
