package com.ms.customer.seat.entity.dto;

import com.ms.customer.seat.entity.SeatNaturalId;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
public record SeatDto(String id,
                      @NotNull SeatNaturalId seatNaturalId,
                      @NotEmpty String roomId) {
}
