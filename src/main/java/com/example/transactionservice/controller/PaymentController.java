package com.example.transactionservice.controller;


import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.dto.TransactionStateDto;
import com.example.transactionservice.dto.TransferRequestDto;
import com.example.transactionservice.dto.WithdrawalRequestDto;
import com.example.transactionservice.model.TopUpRequest;
import com.example.transactionservice.model.TransferRequest;
import com.example.transactionservice.model.WithdrawalRequest;
import com.example.transactionservice.service.TopUpRequestService;
import com.example.transactionservice.service.TransactionService;
import com.example.transactionservice.service.TransferRequestService;
import com.example.transactionservice.service.WithdrawalRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class PaymentController {

    private final TopUpRequestService topUpService;
    private final WithdrawalRequestService withdrawalRequestService;
    private final TransferRequestService transferRequestService;
    private final TransactionService transactionService;

    @PostMapping("/top-up")
    @Transactional
    public TopUpRequest createTopUp(@RequestBody TopUpRequestDto dto) {
        return topUpService.createTopUpRequest(dto);
    }

    @PostMapping("/withdrawal")
    public WithdrawalRequest createWithdrawal(@RequestBody WithdrawalRequestDto dto) {
        return withdrawalRequestService.createWithdrawalRequest(dto);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferRequest> createTransfer(@RequestBody TransferRequestDto dto) {
        var result = transferRequestService.createTransferRequest(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{uid}/status")
    public ResponseEntity<TransactionStateDto> getTransactionsStatus(@PathVariable Long uid) {
        return ResponseEntity.ok(transactionService.getTransactionById(uid));
    }

}
