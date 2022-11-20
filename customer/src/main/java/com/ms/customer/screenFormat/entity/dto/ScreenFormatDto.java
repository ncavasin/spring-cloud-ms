package com.ms.customer.screenFormat.entity.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record ScreenFormatDto(String id, String name, Double screenHeight, Double screenWidth, Set<String> roomIds) {
}
