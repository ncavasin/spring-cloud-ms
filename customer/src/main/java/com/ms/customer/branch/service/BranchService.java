package com.ms.customer.branch.service;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.branch.entity.dto.BranchDto;

import java.util.Set;

public interface BranchService {
    Set<Branch> findAll();

    Branch findById(String id);

    Branch findByName(String name);

    Branch add(BranchDto branchDto);

    Branch update(String id, BranchDto branchDto);

    void delete(String id);
}
