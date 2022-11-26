package com.ms.customer.seat.service;

import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.dto.SeatDto;

import java.util.List;
import java.util.Set;

public interface SeatService {
    List<Seat> findAll();

    List<Seat> findNotSelected();

    List<Seat> findReserved();

    Seat findById(String id);

    Seat add(SeatDto seatDto);

    List<Seat> select(Set<SeatDto> seatDtos);

    List<Seat> confirm(Set<SeatDto> seatDtos);

    void delete(String id);
}
