package com.example.transactionservice.mappers;

import com.example.transactionservice.dto.TransactionStateDto;
import com.example.transactionservice.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionServiceMappers {

    TransactionStateDto map(Transaction transaction);



}
