package com.ms.customer.dto;

import com.ms.customer.Customer;

public class CustomerConverter {

    public static CustomerDto convert(Customer customer) {
        return new CustomerDto(customer.getEmail(), customer.getPassword());
    }
}
