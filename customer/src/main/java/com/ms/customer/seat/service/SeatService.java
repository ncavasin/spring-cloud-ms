package com.ms.customer.seat.service;

import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.entity.dto.SeatNaturalIdDto;

import java.util.List;

public interface SeatService {
    List<Seat> findAll();

    Seat findByNaturalId(String row, Integer column);

    Seat findById(String id);

    List<Seat> findAllByNaturalId(List<SeatNaturalIdDto> seatNaturalIdDtos);

    Seat add(SeatDto seatDto);

    void delete(String id);
}
