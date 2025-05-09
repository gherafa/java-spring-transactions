package com.api.database.transactions;

import com.api.database.customer.CustomerResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponseDto {
    private Long Id;
    private CustomerResponseDto customer;
    private String transactionType;
    private int amount;
    private String description;
}
