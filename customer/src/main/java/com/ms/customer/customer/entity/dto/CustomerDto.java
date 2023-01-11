package com.ms.customer.customer.entity.dto;

import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
public record CustomerDto(String id,
                          @Email @NotEmpty String email,
                          @Size(min = 8, message = "Minimum password length is 8 characters") @NotEmpty String password) {
}
