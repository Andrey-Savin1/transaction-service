package com.example.transactionservice.controller;


import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.dto.WithdrawalRequestDto;
import com.example.transactionservice.model.TopUpRequest;
import com.example.transactionservice.model.WithdrawalRequest;
import com.example.transactionservice.service.TopUpRequestService;
import com.example.transactionservice.service.WithdrawalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private TopUpRequestService topUpService;
    @Autowired
    private WithdrawalRequestService withdrawalRequestService;

    @PostMapping("/top-up")
    public TopUpRequest createTopUp(@RequestBody TopUpRequestDto dto) {
        return topUpService.createTopUpRequest(dto);
    }

    @PostMapping("/withdrawal")
    public WithdrawalRequest createWithdrawal(@RequestBody WithdrawalRequestDto dto) {
        return withdrawalRequestService.createWithdrawalRequest(dto);
    }

}
