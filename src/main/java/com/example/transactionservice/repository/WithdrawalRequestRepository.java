package com.example.transactionservice.repository;

import com.example.transactionservice.model.WithdrawalRequest;
import org.springframework.data.repository.CrudRepository;


public interface WithdrawalRequestRepository extends CrudRepository<WithdrawalRequest, Long> {
}
