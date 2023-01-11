package com.ms.customer.show.service;

import com.ms.customer.show.entity.Show;
import com.ms.customer.show.entity.dto.ShowDto;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

public interface ShowService {

    Set<Show> findAll();

    Set<Show> findByDate(Date date);

    Show findById(String id);

    Set<Show> findByDateAndBeginTimeAndEndTime(Date date, LocalTime beginTime, LocalTime endTime);

    Show add(ShowDto showDto);

    Show update(String id, ShowDto showDto);

    void delete(String id);
}
