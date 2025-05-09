package com.api.database.transactions;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequestDto {
    private String transactionType;
    private int amount;

    public Transaction convertToTransactionEntity() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, Transaction.class);
    }
}
