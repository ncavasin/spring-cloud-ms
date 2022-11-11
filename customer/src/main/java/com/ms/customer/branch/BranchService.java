package com.ms.customer.branch;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BranchService(BranchRepository branchRepository) {

    public List<Branch> getAll() {
        return this.branchRepository.findAll();
    }

    public Branch getById(String branchId) {
        return this.branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFound(String.format("Branch with id %s not found.", branchId)));
    }

    public Branch add(Branch branch) {
        return this.branchRepository.save(branch);
    }

    public Branch update(String branchId, Branch branch) {
        Branch found = this.getById(branchId);
        found.setName(branch.getName());
        found.setZipCode(branch.getZipCode());
        return this.branchRepository.save(found);
    }

    public void delete(String branchId) {
        if (!this.branchRepository.existsById(branchId))
            throw new BadRequest(String.format("Branch with id %s does not exist", branchId));
        this.branchRepository.deleteById(branchId);
    }
}
