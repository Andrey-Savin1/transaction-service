package com.example.transactionservice.repository;

import com.example.transactionservice.model.TransferRequest;
import org.springframework.data.repository.CrudRepository;

public interface TransferRequestRepository extends CrudRepository<TransferRequest, Long> {
}
