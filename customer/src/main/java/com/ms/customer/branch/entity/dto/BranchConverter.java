package com.ms.customer.branch.entity.dto;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.shared.entities.AbstractEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class BranchConverter {

    public static BranchDto convert(Branch branch) {
        Set<String> rooms = branch.getRooms() == null ? null : branch.getRooms()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toSet());
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .zipCode(branch.getZipCode())
                .roomIds(rooms)
                .build();
    }

    public static Set<BranchDto> convert(Set<Branch> branches) {
        return branches.stream()
                .map(BranchConverter::convert)
                .collect(Collectors.toSet());
    }
}
