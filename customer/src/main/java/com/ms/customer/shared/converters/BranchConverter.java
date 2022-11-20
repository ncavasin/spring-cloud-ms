package com.ms.customer.shared.converters;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.branch.entity.dto.BranchDto;

import java.util.Set;
import java.util.stream.Collectors;

public class BranchConverter {

    public static BranchDto convert(Branch branch) {
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .zipCode(branch.getZipCode())
                .build();
    }

    public static Set<BranchDto> convert(Set<Branch> branches) {
        return branches.stream()
                .map(BranchConverter::convert)
                .collect(Collectors.toSet());
    }

    public static Branch convert(BranchDto branchDto) {
        return Branch.builder()
                .name(branchDto.name())
                .zipCode(branchDto.zipCode())
                .build();
    }
}
