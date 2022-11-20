package com.ms.customer.branch.repository;

import com.ms.customer.branch.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {
    @Override
    List<Branch> findAll();

    Optional<Branch> findByName(String name);

    Boolean existsByName(String name);
}
