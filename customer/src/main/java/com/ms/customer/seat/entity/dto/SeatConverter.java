package com.ms.customer.seat.entity.dto;

import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.SeatNaturalId;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SeatConverter {

    public static SeatDto convert(Seat seat) {
        return SeatDto.builder()
                .id(seat.getId())
                .seatNaturalId(seat.getSeatNaturalId())
                .roomId(seat.getRoom().getId())
                .build();
    }

    public static SeatNaturalIdDto convert(SeatNaturalId seatNaturalId) {
        return SeatNaturalIdDto.builder()
                .seatRow(seatNaturalId.getSeatRow())
                .seatColumn(seatNaturalId.getSeatColumn())
                .build();
    }

    public static SeatNaturalId convert(SeatNaturalIdDto seatNaturalIdDto) {
        return SeatNaturalId.builder()
                .seatRow(seatNaturalIdDto.seatRow())
                .seatColumn(seatNaturalIdDto.seatColumn())
                .build();
    }

    public static Set<SeatDto> convert(Set<Seat> seats) {
        return seats.stream()
                .map(SeatConverter::convert)
                .collect(Collectors.toSet());
    }

    public static List<SeatDto> convert(List<Seat> seats) {
        return seats.stream()
                .map(SeatConverter::convert)
                .collect(Collectors.toList());
    }
}
