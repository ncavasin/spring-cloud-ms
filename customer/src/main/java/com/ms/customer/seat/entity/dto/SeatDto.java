package com.ms.customer.seat.entity.dto;

import com.ms.customer.seat.entity.SeatNaturalId;
import lombok.Builder;

@Builder
public record SeatDto(String id, SeatNaturalId seatNaturalId, boolean reserved, String roomId) {
}
