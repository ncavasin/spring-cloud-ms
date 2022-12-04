package com.ms.customer.customer.repository;

import com.ms.customer.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByEmail(String email);
}
