package com.example.transactionservice.service;

import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.model.TopUpRequest;

public interface TopUpRequestService  {


    TopUpRequest createTopUpRequest(TopUpRequestDto dto);

}
