package com.example.transactionservice.repository;

import com.example.transactionservice.model.WithdrawalRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WithdrawalRequestRepository extends CrudRepository<WithdrawalRequest, UUID> {
}
