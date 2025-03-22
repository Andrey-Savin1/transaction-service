package com.example.transactionservice.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class TopUpRequestDto extends RequestDto {

    private String provider;

}
