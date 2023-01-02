package com.ms.customer.show.repository;

import com.ms.customer.show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.time.LocalTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, String> {

    @Query("select (count(s) > 0) from Show s where s.room.id = ?1 and s.date = ?2 and s.beginTime = ?3 and s.endTime = ?4")
    boolean existsByRoomIdAndDateAndBeginTimeAndEndTime(String id, Date date, LocalTime beginTime, LocalTime endTime);

    @Override
    List<Show> findAll();

    List<Show> findByDate(Date date);

    List<Show> findByDateAndBeginTimeAndEndTime(Date date, LocalTime beginTime, LocalTime endTime);
}
