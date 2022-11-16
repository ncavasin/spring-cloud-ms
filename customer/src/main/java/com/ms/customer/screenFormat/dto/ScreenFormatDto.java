package com.ms.customer.screenFormat.dto;

import lombok.Builder;

@Builder
public record ScreenFormatDto(String id, String name, Double screenHeight, Double screenWidth) {
}
