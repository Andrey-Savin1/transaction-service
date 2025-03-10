package com.example.transactionservice.service;

import com.example.transactionservice.dto.WithdrawalRequestDto;
import com.example.transactionservice.model.WithdrawalRequest;
import org.springframework.stereotype.Service;

@Service
public interface WithdrawalRequestService {

    WithdrawalRequest createWithdrawalRequest(WithdrawalRequestDto dto);

}
