package com.api.database.transactions;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import com.api.database.customer.Customer;
import com.api.database.customer.CustomerResponseDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    private Long Id;

    // @OneToOne(cascade = CascadeType.PERSIST)
    // @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "account_no", nullable = false)
    @EqualsAndHashCode.Exclude
    private Customer customer;
    
    
    @Column(nullable = false)
    private String transactionType;

    private int amount;

    private String description;

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    public TransactionResponseDto convertToTransactionResponseDto() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, TransactionResponseDto.class);
    }
}

