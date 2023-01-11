package com.ms.customer.branch;


import com.ms.customer.branch.entity.dto.BranchDto;
import com.ms.customer.branch.service.BranchService;
import com.ms.customer.branch.entity.dto.BranchConverter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/branch")
public record BranchController(BranchService branchService) {

    @GetMapping("/all")
    public Set<BranchDto> getAll() {
        return BranchConverter.convert(this.branchService.findAll());
    }

    @GetMapping("/{branchId}")
    public BranchDto getById(@PathVariable("branchId") String branchId) {
        return BranchConverter.convert(this.branchService.findById(branchId));
    }

    @GetMapping("/name/{branchName}")
    public BranchDto getByName(@PathVariable("branchName") String branchName) {
        return BranchConverter.convert(this.branchService.findByName(branchName));
    }

    @PostMapping()
    public BranchDto add(@Validated @RequestBody BranchDto branchDto) {
        return BranchConverter.convert(this.branchService.add(branchDto));
    }

    @PatchMapping("/{branchId}")
    public BranchDto update(@PathVariable("branchId") String branchId, @Validated @RequestBody BranchDto branchDto) {
        return BranchConverter.convert(branchService.update(branchId, branchDto));
    }

    @DeleteMapping("/{branchId}")
    public void delete(@PathVariable("branchId") String branchId) {
        branchService.delete(branchId);
    }
}
