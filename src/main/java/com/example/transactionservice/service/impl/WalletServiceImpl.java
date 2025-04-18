package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.WalletDto;
import com.example.transactionservice.dto.WalletUpdateDto;
import com.example.transactionservice.model.Wallet;
import com.example.transactionservice.model.enums.CurrencyCode;
import com.example.transactionservice.model.enums.WalletStatus;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.repository.WalletTypeRepository;
import com.example.transactionservice.service.WalletService;
import com.example.transactionservice.service.WalletTypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static com.example.transactionservice.service.impl.TransactionServiceImpl.determineShardValue;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTypeService walletTypeService;
    private final WalletTypeRepository walletTypeRepository;

    @Override
    @Transactional
    public Wallet createWallet(WalletDto dto) {

        var walletType = walletTypeService.getNeedWalletType(dto.getWalletTypeDto());
        log.debug("walletType: {}", walletType.getId());

        Wallet newWallet = new Wallet();
        newWallet.setUserUid(Math.abs(new Random().nextLong()));
        newWallet.setName(dto.getName());
        newWallet.setWalletType(walletType);
        newWallet.setStatus(WalletStatus.ACTIVE);
        newWallet.setBalance(BigDecimal.ZERO);
        log.debug("newWallet: {}", newWallet);

        try (HintManager hintManager = HintManager.getInstance()) {
            Long shardValue = determineShardValue(newWallet.getUserUid());
            hintManager.addDatabaseShardingValue("wallets", shardValue);

            walletRepository.save(newWallet);
        }

        return newWallet;
    }

    public Wallet updateWallet(WalletUpdateDto dto) {

        var findWallet = walletRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (dto.getName() != null) {
            findWallet.setName(dto.getName());
        }
        return walletRepository.save(findWallet);
    }

    @Override
    public List<Wallet> findAllWalletsByUserId(Long id) {
        return walletRepository.findAllByUserUid(id);
    }

    @Override
    public List<Wallet> findAllWalletsByUserIdAndWalletTypeCurrencyCode(Long id, String currencyCode) {

        var type = walletTypeRepository.findByCurrencyCode(CurrencyCode.valueOf(currencyCode));
        return walletRepository.findAllByUserUidAndWalletType(id, type);
    }


}
