package com.example.transactionservice.repository;

import com.example.transactionservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
