package com.ms.customer.seat.service;

import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.SeatNaturalId;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.entity.dto.SeatNaturalIdDto;

import java.util.List;

public interface SeatService {
    List<Seat> findAll();

    List<Seat> findAllById(List<String> ids);

    Seat findByNaturalId(SeatNaturalId seatNaturalId);

    Seat findById(String id);

    List<Seat> findAllByNaturalId(List<SeatNaturalIdDto> seatNaturalIdDtoList);

    Seat add(SeatDto seatDto);

    void delete(String id);
}
