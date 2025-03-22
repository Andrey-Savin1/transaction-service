package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.model.PaymentRequest;
import com.example.transactionservice.model.TopUpRequest;
import com.example.transactionservice.repository.TopUpRequestRepository;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.TopUpRequestService;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.transactionservice.service.impl.TransactionServiceImpl.determineShardValue;


@Service
@Slf4j
@RequiredArgsConstructor
public class TopUpRequestServiceImpl implements TopUpRequestService {

    private final PaymentRequestService paymentRequestService;
    private final TopUpRequestRepository topUpRequestRepository;
    private final TransactionService transactionService;


    @Transactional
    public TopUpRequest createTopUpRequest(TopUpRequestDto dto) {
        PaymentRequest paymentRequest = paymentRequestService.getPaymentRequest(dto);

        if (paymentRequest == null) { throw new RuntimeException("PaymentRequest is null"); }

        try (HintManager hintManager = HintManager.getInstance()) {
            Long shardValue = determineShardValue(paymentRequest.getUserUid());

            log.debug("createTopUpRequest shardValue: {}", shardValue);
            log.debug("paymentRequest: {}", paymentRequest.getUserUid());

            hintManager.addDatabaseShardingValue("transactions", shardValue);
            hintManager.addDatabaseShardingValue("top_up_requests", shardValue);
            hintManager.addDatabaseShardingValue("payment_requests", shardValue);

            transactionService.createTopUpTransaction(dto, paymentRequest);

            TopUpRequest topUpRequest = new TopUpRequest();
            topUpRequest.setProvider(dto.getProvider());
            topUpRequest.setPaymentRequestUid(paymentRequest);

            return topUpRequestRepository.save(topUpRequest);

        }
    }
}
