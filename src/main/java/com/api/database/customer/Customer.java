package com.api.database.customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import com.api.database.transactions.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name =  "customer_id", nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private Long accountNo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long balance;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Transaction> transactions = new ArrayList<>();

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    public CustomerResponseDto convertToCustomerResponseDto() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, CustomerResponseDto.class);
    }

    public void depositToBalance(int amount) {
        this.balance += amount;
    }

    public void withDrawFromBalance(int amount) {
        if(amount > this.balance) {
            throw new InsufficientBalanceException();
        }
        this.balance -= amount;
    }
}
