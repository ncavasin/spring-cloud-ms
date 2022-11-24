package com.ms.customer.seat.repository;

import com.ms.customer.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    @Query(value = "SELECT s from Seat  s")
    List<Seat> findAvailable();

    @Query(value = "SELECT s from Seat  s")
    List<Seat> findReserved();
}
