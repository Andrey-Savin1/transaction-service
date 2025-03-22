package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TransferRequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.TransferRequest;
import com.example.transactionservice.model.enums.PaymentStatus;
import com.example.transactionservice.repository.PaymentRequestRepository;
import com.example.transactionservice.repository.TransferRequestRepository;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.service.TransactionService;
import com.example.transactionservice.service.TransferRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.transactionservice.service.impl.TransactionServiceImpl.determineShardValue;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferRequestServiceImpl implements TransferRequestService {

    private final TransferRequestRepository transferRequestRepository;
    private final PaymentRequestRepository paymentRequestRepository;
    private final WalletRepository walletRepository;
    private final TransactionService transactionService;


    @Transactional
    public TransferRequest createTransferRequest(TransferRequestDto request) {

        var fromWallet = walletRepository.findById(request.getWalletUidFrom())
                .orElseThrow(() -> new IllegalArgumentException("Исходный кошелек не найден"));

        var toWallet = walletRepository.findById(request.getWalletUidTo())
                .orElseThrow(() -> new IllegalArgumentException("Целевой кошелек не найден"));

        if (!(fromWallet.getBalance().subtract(request.getAmount()).compareTo(BigDecimal.ZERO) >= 0)) {
            throw new IllegalArgumentException("Недостаточно средств на счету отправителя");
        }

        //Создание PaymentRequest для отправителя
        var paymentFrom = new PaymentRequest();
        paymentFrom.setUserUid(fromWallet.getUserUid());
        paymentFrom.setWallet(fromWallet);
        paymentFrom.setAmount(request.getAmount().negate()); // Отрицательная сумма
        paymentFrom.setStatus(PaymentStatus.PROCESSING);
        paymentFrom.setComment("Перевод с кошелька " + request.getWalletUidTo());

        // Создание PaymentRequest для получателя
        var paymentTo = new PaymentRequest();
        paymentTo.setUserUid(toWallet.getUserUid());
        paymentTo.setWallet(toWallet);
        paymentTo.setAmount(request.getAmount()); // Положительная сумма
        paymentTo.setStatus(PaymentStatus.PROCESSING);
        paymentTo.setComment("Перевод на кошелек " + request.getWalletUidFrom());
        paymentTo.setCreatedAt(LocalDateTime.now());

        var fromShard = determineShardValue(fromWallet.getUserUid());
        var toShard = determineShardValue(toWallet.getUserUid());

        //Сохраняю данные от кого перевод в отдельную шарду по user_id
        try (HintManager hintManager = HintManager.getInstance()) {
            log.debug("fromShard: {}", fromShard);

            hintManager.addDatabaseShardingValue("transactions", fromShard);
            hintManager.addDatabaseShardingValue("payment_requests", fromShard);

            transactionService.createTransferTransaction(request, paymentFrom);
            paymentRequestRepository.save(paymentFrom);

        }
        //Сохраняю данные кому перевод в отдельную шарду по user_id
        try (HintManager hintManager = HintManager.getInstance()) {
            log.debug("toShard : {}", toShard);

            hintManager.addDatabaseShardingValue("transactions", toShard);
            hintManager.addDatabaseShardingValue("payment_requests", toShard);

            transactionService.createTransferTransaction(request, paymentTo);
            paymentRequestRepository.save(paymentTo);
        }

        //Отдельно сохраняю данные по переводу в шарду юзера от которого перевод.
        var transferRequest = new TransferRequest();
        try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.addDatabaseShardingValue("transfer_requests", fromShard);

            transferRequest.setPaymentRequestUidTo(paymentTo.getId());
            transferRequest.setPaymentRequestUidFrom(paymentFrom.getId());
            if (!fromWallet.getWalletType().getCurrencyCode().equals(toWallet.getWalletType().getCurrencyCode())) {
                transferRequest.setSystemRate("1.35");
            } else {
                transferRequest.setSystemRate("1.0");
            }
            transferRequestRepository.save(transferRequest);
        }
        return transferRequest;
    }
}
