package com.ms.customer.seat.entity.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
public record SeatNaturalIdDto(String id,
                               @NotEmpty String seatRow,
                               @NotNull @Positive(message = "Columns begin at number 1") Integer seatColumn) {
}
