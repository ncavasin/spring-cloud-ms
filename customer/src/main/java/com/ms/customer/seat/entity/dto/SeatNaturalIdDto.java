package com.ms.customer.seat.entity.dto;

import lombok.Builder;

@Builder
public record SeatNaturalIdDto(String seatRow, Integer seatColumn) {
}
