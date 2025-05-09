package com.api.database.customer;

import java.util.List;

import com.api.database.transactions.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {
    private Long customerId;
    private String name;
    private Long accountNo;
    private Long balance;
}
