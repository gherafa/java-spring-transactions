package com.api.database.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer save (Customer customer) {
        Optional<Customer> searchedcustomer = customerRepository.findByNameContains(customer.getName());
        Optional<Customer> selectedCustomer = customerRepository.findByAccountNo(customer.getAccountNo());
        if (searchedcustomer.isPresent() || selectedCustomer.isPresent()) {
            throw new CustomerAlreadyAddedException();
        }
        if (customer.getAccountNo() == null) {
            throw new AccountNoMustBeDefinedException();
        }
        return customerRepository.save(customer);
    }

    public List<Customer> fetchAll() {
        return customerRepository.findAll();
    }

    public Customer fetchOneByAccountNo(Long accountNo) {
        Optional<Customer> selectedCustomer = customerRepository.findByAccountNo(accountNo);
        if (!selectedCustomer.isPresent()) {
            throw new CustomerNotFoundException();
        }

        return selectedCustomer.get();
    }
}
