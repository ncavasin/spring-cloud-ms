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

    private static final int MIN_PWD_LENGTH = 8;

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
        checkEmailIsTaken(customerDto.email());
        checkPasswordLength(customerDto.password());
        // TODO: validate email format
        // TODO: implement HASH pwd
        log.info("Customer with email '{}' created.", customerDto.email());
        return customerRepository.save(Customer.builder()
                .email(customerDto.email())
                .password(hashPassword(customerDto.password()))
                .build());
    }

    public Customer update(String id, CustomerDto customerDto) {
        checkCustomerExists(id);

        Customer customer = this.findById(id);
        if (customerDto.email() != null && !customerDto.email().isEmpty() && !customer.getEmail().equals(customerDto.email())) {
            checkEmailIsTaken(customerDto.email());
            customer.setEmail(customerDto.email());
        }
        if (customerDto.password() != null && !customerDto.password().isEmpty() && !customer.getPassword().equals(customerDto.password())) {
            checkPasswordLength(customerDto.password());
            customer.setPassword(hashPassword(customerDto.password()));
        }
        log.info("Customer with id '{}' updated.", id);
        return this.customerRepository.save(customer);
    }

    public void delete(String customerId) {
        checkCustomerExists(customerId);
        this.logger.info("Customer with id '{}' deleted.", customerId);
        this.customerRepository.deleteById(customerId);
    }

    private void checkEmailIsTaken(String email) {
        if (this.customerRepository.existsByEmail(email)) {
            log.warn("Email '{}' already taken", email);
            throw new BadRequest(String.format("Email %s already taken!", email));
        }
    }

    private void checkCustomerExists(String customerId) {
        if (!this.customerRepository.existsById(customerId)) {
            log.warn("Customer with id '{}' does not exist", customerId);
            throw new NotFound(String.format("Customer with id %s does not exist", customerId));
        }
    }

    private void checkPasswordLength(String password) {
        if (password.length() < MIN_PWD_LENGTH) {
            log.warn("Password minimum length ({}) not matched.", MIN_PWD_LENGTH);
            throw new BadRequest(String.format("Password minimum length (%s) not matched", MIN_PWD_LENGTH));
        }
    }

    private String hashPassword(String password) {
        return password;
    }
}
