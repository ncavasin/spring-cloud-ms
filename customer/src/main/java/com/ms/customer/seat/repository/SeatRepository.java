package com.ms.customer.seat.repository;

import com.ms.customer.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findAvailable();

    List<Seat> findReserved();
}
