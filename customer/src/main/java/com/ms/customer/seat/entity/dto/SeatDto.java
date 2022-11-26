package com.ms.customer.seat.entity.dto;

import com.ms.customer.seat.entity.SeatNaturalId;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record SeatDto(String id, SeatNaturalId seatNaturalId, String roomId, Timestamp selection,
                      Timestamp confirmation) {
}
