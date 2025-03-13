package com.example.transactionservice.repository;

import com.example.transactionservice.model.TopUpRequest;
import org.springframework.data.repository.CrudRepository;

public interface TopUpRequestRepository extends CrudRepository<TopUpRequest, Long> {
}
