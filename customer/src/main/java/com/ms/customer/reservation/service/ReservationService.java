package com.ms.customer.reservation.service;

import com.ms.customer.reservation.entity.Reservation;
import com.ms.customer.reservation.entity.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();

    Reservation findById(String id);

    Reservation add(ReservationDto reservationDto);

    void delete(String id);
}
