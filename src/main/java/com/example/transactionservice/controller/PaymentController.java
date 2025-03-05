package com.example.transactionservice.controller;


import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.model.TopUpRequest;
import com.example.transactionservice.service.TopUpRequestService;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private TopUpRequestService topUpService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/top-up")
    public TopUpRequest createTopUp(@RequestBody TopUpRequestDto dto) {
        return topUpService.createTopUpRequest(
                dto.getUserUid(),
                dto.getWalletUid(),
                dto.getAmount(),
                dto.getComment(),
                dto.getProvider()
        );
    }

    @PostMapping("/process-top-up/{paymentRequestUid}")
    public void processTopUp(@PathVariable UUID paymentRequestUid) {
        transactionService.processTopUpTransaction(paymentRequestUid);
    }



}
