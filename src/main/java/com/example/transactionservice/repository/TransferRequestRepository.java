package com.example.transactionservice.repository;

import com.example.transactionservice.model.TransferRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransferRequestRepository extends CrudRepository<TransferRequest, UUID> {
}
