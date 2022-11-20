package com.ms.customer.room.entity.dto;

import lombok.Builder;

@Builder
public record RoomDto(String id, String name, String branchId) {
}
