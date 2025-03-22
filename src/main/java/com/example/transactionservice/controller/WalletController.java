package com.example.transactionservice.controller;


import com.example.transactionservice.dto.WalletDto;
import com.example.transactionservice.dto.WalletUpdateDto;
import com.example.transactionservice.model.Wallet;
import com.example.transactionservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/create")
    @Transactional
    public Wallet createWallet(@RequestBody WalletDto walletDto) {
        return walletService.createWallet(walletDto);
    }

    @PatchMapping("/update")
    public Wallet updateWallet(@RequestBody WalletUpdateDto walletDto) {
        return walletService.updateWallet(walletDto);
    }

    @GetMapping("/{user_uid}")
    public List<Wallet> getAllWalletsByUserId(@PathVariable("user_uid") Long userId) {
        return walletService.findAllWalletsByUserId(userId);
    }

    @GetMapping("/{userId}/currency/{currency}")
    public List<Wallet> getAllWalletsByUserIdAndCurrency(@PathVariable("userId") Long userId, @PathVariable String currency) {
        return walletService.findAllWalletsByUserIdAndWalletTypeCurrencyCode(userId, currency);

    }

}
