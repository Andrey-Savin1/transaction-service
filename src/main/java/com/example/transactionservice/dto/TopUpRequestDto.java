package com.example.transactionservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TopUpRequestDto extends RequestDto {

    private String provider;

}
