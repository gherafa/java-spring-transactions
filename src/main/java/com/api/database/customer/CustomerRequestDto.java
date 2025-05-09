package com.api.database.customer;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDto {
    private Long customerId;
    private String name;
    private Long accountNo;
    private Long balance;

    public Customer convertToCustomerEntity() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, Customer.class);
    }
}
