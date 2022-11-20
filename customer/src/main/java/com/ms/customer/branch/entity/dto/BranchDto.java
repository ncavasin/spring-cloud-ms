package com.ms.customer.branch.entity.dto;

import lombok.Builder;

@Builder
public record BranchDto(String id, String name, String zipCode) {
}
