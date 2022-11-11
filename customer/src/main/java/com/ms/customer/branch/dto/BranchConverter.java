package com.ms.customer.branch.dto;

import com.ms.customer.branch.Branch;
import com.ms.customer.branch.BranchDto;

import java.util.List;
import java.util.stream.Collectors;

public class BranchConverter {

    public static BranchDto convert(Branch branch) {
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .zipCode(branch.getZipCode())
                .build();
    }

    public static List<BranchDto> convert(List<Branch> branches) {
        return branches.stream()
                .map(BranchConverter::convert)
                .collect(Collectors.toList());
    }

    public static Branch convert(BranchCreationDto branchCreationDto) {
        return Branch.builder()
                .name(branchCreationDto.name())
                .zipCode(branchCreationDto.zipCode())
                .build();
    }

    public static Branch convert(BranchDto branchDto) {
        return Branch.builder()
                .name(branchDto.name())
                .zipCode(branchDto.zipCode())
                .build();
    }
}
