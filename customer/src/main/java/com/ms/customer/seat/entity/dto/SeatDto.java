package com.ms.customer.seat.entity.dto;

import com.ms.customer.seat.SeatNaturalId;
import lombok.Builder;

@Builder
public record SeatDto(String id, SeatNaturalId seatNaturalId) {
}
