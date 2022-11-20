package com.ms.customer.branch.entity.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record BranchDto(String id, String name, String zipCode, Set<String> roomIds) {
}
