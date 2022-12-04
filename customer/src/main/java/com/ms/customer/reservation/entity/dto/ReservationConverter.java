package com.ms.customer.reservation.entity.dto;

import com.ms.customer.reservation.entity.Reservation;
import com.ms.customer.seat.entity.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationConverter {

    public static ReservationDto convert(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .customerId(reservation.getCustomer().getId())
                .seatIds(reservation.getSeats().stream()
                        .map(Seat::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static List<ReservationDto> convert(List<Reservation> reservationList) {
        return reservationList.stream()
                .map(ReservationConverter::convert)
                .collect(Collectors.toList());
    }
}
