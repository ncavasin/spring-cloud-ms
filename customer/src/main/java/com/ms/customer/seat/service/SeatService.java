package com.ms.customer.seat.service;

import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.dto.SeatDto;

import java.util.Set;

public interface SeatService {
    Set<Seat> findAll();

    Set<Seat> findAvailable();

    Set<Seat> findReserved();

    Seat findById(String id);

    Seat add(SeatDto seatDto);

    Seat update(String id, SeatDto seatDto);

    void delete(String id);
}
