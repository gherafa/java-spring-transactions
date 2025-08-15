package com.api.database.transactions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.database.customer.Customer;
import com.api.database.customer.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
    private final CustomerService customerService;
    private final TransactionRepository transactionRepository;
    
    public List<Transaction> makeTransaction(Transaction transaction, Long sourceAccountNo, Long targetAccountNo) {
        Customer sourceCustomer = customerService.fetchOneByAccountNo(sourceAccountNo);
        Customer targetCustomer = customerService.fetchOneByAccountNo(targetAccountNo);

        targetCustomer.depositToBalance(transaction.getAmount());
        sourceCustomer.withDrawFromBalance(transaction.getAmount());

        Transaction withdrawalTransaction = Transaction.builder().customer(sourceCustomer)
            .transactionType(transaction.getTransactionType()).amount(transaction.getAmount()).description("WITHDRAWAL").build();

        Transaction depositTransaction = Transaction.builder().customer(targetCustomer)
            .transactionType(transaction.getTransactionType()).amount(transaction.getAmount()).description("DEPOSIT").build();

        withdrawalTransaction = transactionRepository.save(withdrawalTransaction);
        depositTransaction = transactionRepository.save(depositTransaction);

        return List.of(withdrawalTransaction, depositTransaction);
    }

    public List<Transaction> fetchAllTransactions(Long accountNo) {
        List<Transaction> allTransactions = transactionRepository.findAll();

        return allTransactions.stream()
            .filter(transaction -> transaction.getCustomer().getAccountNo().equals(accountNo))
            .collect(Collectors.toList());
    }
}
