package com.ms.customer.customer.service;

import com.ms.customer.customer.entity.Customer;
import com.ms.customer.customer.entity.dto.CustomerDto;
import com.ms.customer.customer.repository.CustomerRepository;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public record CustomerServiceImpl(CustomerRepository customerRepository, Logger logger) implements CustomerService {
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public Customer findById(String customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.warn("Customer with id '{}' not found", customerId);
                    throw new NotFound(String.format("Customer with id %s not found!", customerId));
                });
    }

    public Customer add(CustomerDto customerDto) {
        emailIsTaken(customerDto.email());
        // TODO: validate email format
        log.info("Customer with email '{}' created.", customerDto.email());
        return customerRepository.save(Customer.builder()
                .email(customerDto.email())
                .password(customerDto.password())
                .build());
    }

    public Customer update(String id, CustomerDto customerDto) {
        validateCustomerExists(id);
        emailIsTaken(customerDto.email());
        Customer customer = this.findById(id);
        // TODO: hash password
        customer.setEmail(customerDto.email());
        customer.setPassword(customer.getPassword());
        log.info("Customer with id '{}' updated.", id);
        return this.customerRepository.save(customer);
    }

    public void delete(String customerId) {
        validateCustomerExists(customerId);
        this.logger.info("Customer with id '{}' deleted.", customerId);
        this.customerRepository.deleteById(customerId);
    }

    private void emailIsTaken(String email) {
        if (this.customerRepository.existsByEmail(email)) {
            log.warn("Email '{}' already taken", email);
            throw new BadRequest(String.format("Email %s already taken!", email));
        }
    }

    private void validateCustomerExists(String customerId) {
        if (!this.customerRepository.existsById(customerId)) {
            log.warn("Customer with id '{}' does not exist", customerId);
            throw new BadRequest(String.format("Customer with id %s does not exist", customerId));
        }
    }
}
