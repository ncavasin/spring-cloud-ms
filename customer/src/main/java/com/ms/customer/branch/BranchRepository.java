package com.ms.customer.branch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {
    @Override
    List<Branch> findAll();

    Optional<Branch> findByName(String name);
}
