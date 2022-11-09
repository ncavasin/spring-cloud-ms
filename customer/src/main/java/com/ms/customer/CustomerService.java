package com.ms.customer;

import com.ms.customer.dto.CustomerCreationDto;
import com.ms.customer.dto.CustomerDto;
import com.ms.shared.exceptions.BadRequest;
import com.ms.shared.exceptions.NotFound;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public Customer createCustomer(CustomerCreationDto customerCreationDto) {
        emailIsTaken(customerCreationDto.email());


        Customer customer = Customer.builder()
                .email(customerCreationDto.email())
                .password(customerCreationDto.password())
                .build();

        // Check email is valid
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(String customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFound(String.format("Customer with id {} not found!", customerId)));
    }

    public Customer updateCustomer(String customerId, CustomerDto customerDto) {
        Customer found = this.getCustomerById(customerId);
        emailIsTaken(customerDto.email());
        found.setEmail(customerDto.email());
        found.setPassword(customerDto.password());
        return this.customerRepository.save(found);
    }

    public void deleteCustomer(String customerId) {
        if (!this.customerRepository.existsById(customerId)) {
            throw new EntityNotFoundException(String.format("Customer with id {} not found!", customerId));
        }
        this.customerRepository.deleteById(customerId);
    }

    private void emailIsTaken(String email) {
        if (this.customerRepository.existsByEmail(email)) {
            throw new BadRequest(String.format("Email {} already taken!", email));
        }
    }
}
