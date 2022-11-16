package com.ms.customer.branch;


import com.ms.customer.branch.dto.BranchConverter;
import com.ms.customer.branch.dto.BranchCreationDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
public record BranchController(BranchService branchService) {

    @GetMapping("/all")
    public List<BranchDto> getAll() {
        return BranchConverter.convert(this.branchService.getAll());
    }

    @GetMapping("/{branchId}")
    public BranchDto getById(@PathVariable("branchId") String branchId) {
        return BranchConverter.convert(this.branchService.getById(branchId));
    }

    @GetMapping("/name/{branchName}")
    public BranchDto getByName(@PathVariable("branchName") String branchName) {
        return BranchConverter.convert(this.branchService.getByName(branchName));
    }

    @PostMapping()
    public BranchDto add(@Validated @RequestBody BranchCreationDto branchCreationDto) {
        return BranchConverter.convert(this.branchService.add(BranchConverter.convert(branchCreationDto)));
    }

    @PatchMapping("/{branchId}")
    public BranchDto update(@PathVariable("branchId") String branchId, @Validated @RequestBody BranchDto branchDto) {
        return BranchConverter.convert(branchService.update(branchId, BranchConverter.convert(branchDto)));
    }

    @DeleteMapping("/{branchId}")
    public void delete(@PathVariable("branchId") String branchId) {
        branchService.delete(branchId);
    }
}
