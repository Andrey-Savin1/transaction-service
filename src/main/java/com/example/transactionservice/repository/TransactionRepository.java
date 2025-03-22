package com.example.transactionservice.repository;

import com.example.transactionservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
