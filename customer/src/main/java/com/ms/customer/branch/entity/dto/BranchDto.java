package com.ms.customer.branch.entity.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Builder
public record BranchDto(String id,
                        @NotEmpty String name,
                        @NotEmpty String zipCode,
                        Set<String> roomIds) {
}
