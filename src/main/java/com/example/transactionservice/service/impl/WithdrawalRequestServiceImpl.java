package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.WithdrawalRequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.WithdrawalRequest;
import com.example.transactionservice.repository.WithdrawalRequestRepository;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.TransactionService;
import com.example.transactionservice.service.WithdrawalRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;

import static com.example.transactionservice.service.impl.TransactionServiceImpl.determineShardValue;


@Service
@Slf4j
@RequiredArgsConstructor()
public class WithdrawalRequestServiceImpl implements WithdrawalRequestService {

    private final TransactionService transactionService;
    private final WithdrawalRequestRepository withdrawalRequestRepository;
    private final PaymentRequestService paymentRequestService;


    public WithdrawalRequest createWithdrawalRequest(WithdrawalRequestDto dto) {
        PaymentRequest paymentRequest = paymentRequestService.getPaymentRequest(dto);

        if (paymentRequest == null) { throw new RuntimeException("PaymentRequest is null"); }

        try (HintManager hintManager = HintManager.getInstance()) {
            Long shardValue = determineShardValue(paymentRequest.getUserUid());

            log.debug("createWithdrawalRequest shardValue: {}", shardValue);
            log.debug("paymentRequest: {}", paymentRequest.getUserUid());

            hintManager.addDatabaseShardingValue("transactions", shardValue);
            hintManager.addDatabaseShardingValue("withdrawal_requests", shardValue);
            hintManager.addDatabaseShardingValue("payment_requests", shardValue);

            WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
            withdrawalRequest.setPaymentRequestUid(paymentRequest);

            transactionService.createWithdrawalTransaction(dto, paymentRequest);

            return withdrawalRequestRepository.save(withdrawalRequest);
        }
    }
}
