package com.ms.customer.customer.service;

import com.ms.customer.customer.entity.Customer;
import com.ms.customer.customer.entity.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(String id);

    Customer add(CustomerDto customerDto);

    Customer update(String id, CustomerDto customerDto);

    void delete(String id);
}
