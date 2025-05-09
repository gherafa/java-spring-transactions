package com.api.database.transactions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/customers/transactions/{sourceAccountNo}/{targetAccountNo}")
    public ResponseEntity<List<TransactionResponseDto>> addTransaction(
            @RequestBody TransactionRequestDto transactionRequestDto, @PathVariable Long sourceAccountNo,
            @PathVariable Long targetAccountNo) {
        Transaction transactionEntity = transactionRequestDto.convertToTransactionEntity();
        List<Transaction> savedTransactions = transactionService.makeTransaction(transactionEntity, sourceAccountNo, targetAccountNo);
        // TransactionResponseDto transactionResponseDto = savedTransaction.convertToTransactionResponseDto();

        List<TransactionResponseDto> transactionResponseDtos = savedTransactions.stream()
            .map(Transaction::convertToTransactionResponseDto)
            .collect(Collectors.toList());

        return new ResponseEntity<>(transactionResponseDtos, HttpStatus.CREATED);
    }

    @GetMapping("/customers/{accountNo}/transactions")
    public List<TransactionResponseDto>fetchTransactions(@PathVariable Long accountNo) {
        List<Transaction> fetchedTransactions = transactionService.fetchAllTransactions(accountNo);

        return fetchedTransactions.stream().map(Transaction::convertToTransactionResponseDto).collect(Collectors.toList());
    }

}

