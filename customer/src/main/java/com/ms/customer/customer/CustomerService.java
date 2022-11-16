package com.ms.customer.customer;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

    public Customer getById(String customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFound(String.format("Customer with id %s not found!", customerId)));
    }

    public Customer add(Customer customer) {
        emailIsTaken(customer.getEmail());
        // TODO: validate email format
        return customerRepository.save(customer);
    }

    public Customer update(String customerId, Customer customer) {
        validateCustomerExists(customerId);
        emailIsTaken(customer.getEmail());
        customer.setId(customerId);
        // TODO: hash password
        return this.customerRepository.save(customer);
    }

    public void delete(String customerId) {
        if (!this.customerRepository.existsById(customerId)) {
            throw new NotFound(String.format("Customer with id %s not found!", customerId));
        }
        this.customerRepository.deleteById(customerId);
    }

    private void emailIsTaken(String email) {
        if (this.customerRepository.existsByEmail(email)) {
            throw new BadRequest(String.format("Email %s already taken!", email));
        }
    }

    private void validateCustomerExists(String customerId) {
        if (!this.customerRepository.existsById(customerId)) {
            throw new BadRequest(String.format("Customer with id %s does not exist", customerId));
        }
    }
}
