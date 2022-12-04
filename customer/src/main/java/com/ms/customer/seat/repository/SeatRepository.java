package com.ms.customer.seat.repository;

import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.SeatNaturalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, String> {
    @Override
    List<Seat> findAll();

    @Query("SELECT s FROM Seat s WHERE s.seatNaturalId = ?1")
    Optional<Seat> findBySeatNaturalId(SeatNaturalId seatNaturalId);

    @Query("select s from Seat s where s.seatNaturalId = ?1")
    List<Seat> findAllBySeatNaturalId(List<SeatNaturalId> seatNaturalIds);

    @Query("select (count(s) > 0) from Seat s where s.seatNaturalId = ?1")
    boolean existsBySeatNaturalId(SeatNaturalId seatNaturalId);
}
