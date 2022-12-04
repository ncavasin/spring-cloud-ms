package com.ms.customer.reservation.repository;

import com.ms.customer.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    @Override
    List<Reservation> findAll();
}
