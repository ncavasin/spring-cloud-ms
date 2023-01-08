package com.ms.customer.branch.service;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.branch.entity.dto.BranchDto;
import com.ms.customer.branch.repository.BranchRepository;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public record BranchServiceImpl(BranchRepository branchRepository, Logger logger) implements BranchService {

    public Set<Branch> findAll() {
        return new HashSet<>(this.branchRepository.findAll());
    }

    public Branch findById(String branchId) {
        return this.branchRepository.findById(branchId)
                .orElseThrow(() -> {
                    logger.warn("Branch with id '{}' not found.", branchId);
                    throw new NotFound(String.format("Branch with id %s not found.", branchId));
                });
    }

    public Branch findByName(String name) {
        return this.branchRepository().findByName(name)
                .orElseThrow(() -> {
                    logger.warn("Branch with name '{}' not found", name);
                    throw new NotFound(String.format("Branch with name %s not found.", name));
                });
    }

    public Branch add(BranchDto branchDto) {
        checkNameIsUnique(branchDto.name());
        logger.info("Branch with name '{}' created.", branchDto.name());
        return this.branchRepository.save(Branch.builder()
                .name(branchDto.name())
                .zipCode(branchDto.zipCode())
                .build());
    }

    public Branch update(String branchId, BranchDto branchDto) {
        Branch found = this.findById(branchId);
        checkNameIsUnique(branchDto.name());
        found.setName(branchDto.name());
        found.setZipCode(branchDto.zipCode());
        logger.info("Branch with id '{}' updated.", branchId);
        return this.branchRepository.save(found);
    }

    public void delete(String branchId) {
        if (!this.branchRepository.existsById(branchId)) {
            logger.warn("Could not delete branch with id '{}'. It does not exist.", branchId);
            throw new NotFound(String.format("Branch with id %s does not exist", branchId));
        }
        this.branchRepository.deleteById(branchId);
    }

    private void checkNameIsUnique(String name) {
        if (this.branchRepository.existsByName(name)) {
            logger.warn("Branch name '{}' already exists.", name);
            throw new BadRequest(String.format("Branch name %s already exists.", name));
        }
    }
}
