package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.RequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.enums.PaymentStatus;
import com.example.transactionservice.repository.PaymentRequestRepository;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.repository.WalletTypeRepository;
import com.example.transactionservice.service.PaymentRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentRequestServiceImpl implements PaymentRequestService {

    private final WalletRepository walletRepository;


    @Transactional
    public <T extends RequestDto> PaymentRequest getPaymentRequest(T dto) {
        return createPaymentRequest(dto.getUserUid(),
                dto.getAmount(), dto.getComment(), dto.getCurrency());
    }

    @Transactional
    public PaymentRequest createPaymentRequest(Long userUid, BigDecimal amount, String comment, String currency) {

        var wallet = walletRepository.findByUserUid(userUid);

        if (wallet.isPresent()) {
            if (wallet.get().getWalletType().getCurrencyCode().name().equals(currency.toUpperCase())) {

                System.out.println("wallet: " + wallet.get().getId());

                PaymentRequest paymentRequest = new PaymentRequest();
                paymentRequest.setUserUid(userUid);
                paymentRequest.setWallet(wallet.orElseThrow());
                paymentRequest.setAmount(amount);
                paymentRequest.setStatus(PaymentStatus.PROCESSING);
                paymentRequest.setComment(comment);

                return paymentRequest;
            }
        }
        return null;
    }

}
