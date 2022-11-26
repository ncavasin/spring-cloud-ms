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

    @Query(value = "SELECT s FROM Seat s WHERE (s.selectionExpiration <= CURRENT_TIMESTAMP)")
    List<Seat> findNotSelected();

    @Query(value = "SELECT s FROM Seat s WHERE s.confirmation is null")
    List<Seat> findNotConfirmed();

    @Query(value = "SELECT s FROM Seat s WHERE s.confirmation <= s.selectionExpiration")
    List<Seat> findConfirmed();

    @Query("select (count(s) > 0) from Seat s where s.seatNaturalId = ?1")
    boolean existsBySeatNaturalId(SeatNaturalId seatNaturalId);
}
