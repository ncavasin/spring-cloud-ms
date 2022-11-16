package com.ms.customer.customer.dto;

import com.ms.customer.customer.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerConverter {

    public static Customer convert(CustomerDto customerDto){
        return Customer.builder()
                .email(customerDto.email())
                .password(customerDto.password())
                .build();
    }

    public static CustomerDto convert(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getEmail(), customer.getPassword());
    }

    public static List<CustomerDto> convert(List<Customer> customers) {
        return customers.stream()
                .map(CustomerConverter::convert)
                .collect(Collectors.toList());
    }
}
