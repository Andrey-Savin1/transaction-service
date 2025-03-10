package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.RequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.WalletType;
import com.example.transactionservice.model.enums.PaymentStatus;
import com.example.transactionservice.repository.PaymentRequestRepository;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.repository.WalletTypeRepository;
import com.example.transactionservice.service.PaymentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentRequestServiceImpl implements PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletTypeRepository walletTypeRepository;


    @Transactional
    public PaymentRequest createPaymentRequest(UUID userUid, BigDecimal amount, String comment, String currency) {

        var wallet = walletRepository.findByUserUid(userUid);

        if (wallet.isPresent()){
            if (wallet.get().getWalletType().getCurrencyCode().name().equals(currency.toUpperCase())){

                PaymentRequest paymentRequest = new PaymentRequest();
                paymentRequest.setUserUid(userUid);
                paymentRequest.setWallet(wallet.orElseThrow());
                paymentRequest.setAmount(amount);
                paymentRequest.setStatus(PaymentStatus.PROCESSING);
                paymentRequest.setComment(comment);
                return paymentRequestRepository.save(paymentRequest);
            }
        }

        return null;

    }

    @Transactional
    public  <T extends RequestDto> PaymentRequest getPaymentRequest(T dto) {
        return createPaymentRequest(dto.getUserUid(),
                dto.getAmount(), dto.getComment(), dto.getCurrency());
    }

}
