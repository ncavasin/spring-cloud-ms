package com.ms.customer.branch;

import lombok.Builder;

@Builder
public record BranchDto(String id, String name, String zipCode) {
}
