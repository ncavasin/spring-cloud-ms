package com.ms.customer.customer.dto;

import lombok.Builder;

@Builder
public record CustomerDto(String id, String email, String password) {
}
