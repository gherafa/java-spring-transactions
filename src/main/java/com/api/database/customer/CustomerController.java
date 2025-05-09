package com.api.database.customer;

import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        Customer customer = customerRequestDto.convertToCustomerEntity();
        customerService.save(customer);
        CustomerResponseDto customerResponseDto = customer.convertToCustomerResponseDto();

        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public List<CustomerResponseDto> fetchAllcustomers() {
        List<Customer> customers = customerService.fetchAll();

        return customers.stream().map(Customer::convertToCustomerResponseDto).collect(toList());
    }

    @GetMapping("/customers/{accountNo}")
    public CustomerResponseDto fetchCustomer(@PathVariable Long accountNo) {
        Customer customer = customerService.fetchOneByAccountNo(accountNo);

        return customer.convertToCustomerResponseDto();
    }
}
