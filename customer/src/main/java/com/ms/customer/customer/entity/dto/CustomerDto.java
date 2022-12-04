package com.ms.customer.customer.entity.dto;

import lombok.Builder;

@Builder
public record CustomerDto(String id, String email, String password) {
}
