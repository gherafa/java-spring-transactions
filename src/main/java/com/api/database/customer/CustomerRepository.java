package com.api.database.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByNameContains(String name);
    Optional<Customer> findByAccountNo(Long accountNo);
}
