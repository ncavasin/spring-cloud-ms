package com.ms.customer.seat.entity.dto;

import com.ms.customer.seat.entity.Seat;

import java.util.Set;
import java.util.stream.Collectors;

public class SeatConverter {

    public static SeatDto convert(Seat seat) {
        return SeatDto.builder()
                .id(seat.getId())
                .seatNaturalId(seat.getSeatNaturalId())
                .build();
    }

    public static Set<SeatDto> convert(Set<Seat> seats) {
        return seats.stream()
                .map(SeatConverter::convert)
                .collect(Collectors.toSet());
    }
}
