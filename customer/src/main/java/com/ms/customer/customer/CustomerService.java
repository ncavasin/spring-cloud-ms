package com.ms.customer.customer;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository, Logger logger) {
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

    public Customer getById(String customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.info("Customer with id '{}' not found", customerId);
                    throw new NotFound(String.format("Customer with id %s not found!", customerId));
                });
    }

    public Customer add(Customer customer) {
        emailIsTaken(customer.getEmail());
        log.info("New customer with email '{}' created.", customer.email);
        // TODO: validate email format
        return customerRepository.save(customer);
    }

    public Customer update(String customerId, Customer customer) {
        validateCustomerExists(customerId);
        emailIsTaken(customer.getEmail());
        customer.setId(customerId);
        log.info("Customer with id '{}' updated to: '{}'.", customerId, customer);
        // TODO: hash password
        return this.customerRepository.save(customer);
    }

    public void delete(String customerId) {
        if (!this.customerRepository.existsById(customerId)) {
            throw new NotFound(String.format("Customer with id %s not found!", customerId));
        }
        this.logger.info("Customer with id '{}' deleted.", customerId);
        this.customerRepository.deleteById(customerId);
    }

    private void emailIsTaken(String email) {
        if (this.customerRepository.existsByEmail(email)) {
            log.info("Email '{}' already taken", email);
            throw new BadRequest(String.format("Email %s already taken!", email));
        }
    }

    private void validateCustomerExists(String customerId) {
        if (!this.customerRepository.existsById(customerId)) {
            log.info("Customer with id '{}' does not exist", customerId);
            throw new BadRequest(String.format("Customer with id %s does not exist", customerId));
        }
    }
}
