package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransferRequestDto;
import com.example.transactionservice.model.TransferRequest;

public interface TransferRequestService {

    TransferRequest createTransferRequest(TransferRequestDto dto);

}
