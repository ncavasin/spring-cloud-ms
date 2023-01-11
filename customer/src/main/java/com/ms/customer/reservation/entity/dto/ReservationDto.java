package com.ms.customer.reservation.entity.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ReservationDto(String id, String customerId, List<String> seatIds) {
}
