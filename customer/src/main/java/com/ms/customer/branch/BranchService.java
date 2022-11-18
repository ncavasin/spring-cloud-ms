package com.ms.customer.branch;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BranchService(BranchRepository branchRepository, Logger logger) {

    public List<Branch> getAll() {
        return this.branchRepository.findAll();
    }

    public Branch getById(String branchId) {
        return this.branchRepository.findById(branchId)
                .orElseThrow(() -> {
                    logger.warn("Branch with id '{}' not found.", branchId);
                    throw new NotFound(String.format("Branch with id %s not found.", branchId));
                });
    }

    public Branch getByName(String name) {
        return this.branchRepository().findByName(name)
                .orElseThrow(() -> {
                    logger.warn("Branch with name '{}' not found", name);
                    throw new NotFound(String.format("Branch with name %s not found.", name));
                });
    }

    public Branch add(Branch branch) {
        if (this.branchRepository.existsByName(branch.getName())) {
            logger.warn("Could not add Branch with name '{}'. It already exists.", branch.getName());
            throw new BadRequest(String.format("Branch with name %s already exists.", branch.getName()));
        }
        logger.info("Branch with name '{}' created.", branch.getName());
        return this.branchRepository.save(branch);
    }

    public Branch update(String branchId, Branch branch) {
        Branch found = this.getById(branchId);
        found.setName(branch.getName());
        found.setZipCode(branch.getZipCode());
        logger.info("Branch with id '{}' updated.", branchId);
        return this.branchRepository.save(found);
    }

    public void delete(String branchId) {
        if (!this.branchRepository.existsById(branchId)) {
            logger.warn("Could not delete branch with id '{}'. It does not exist.", branchId);
            throw new BadRequest(String.format("Branch with id %s does not exist", branchId));
        }
        this.branchRepository.deleteById(branchId);
    }
}
