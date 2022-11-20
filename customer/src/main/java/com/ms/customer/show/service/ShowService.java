package com.ms.customer.show.service;

import com.ms.customer.show.dto.ShowDto;
import com.ms.customer.show.entity.Show;

import java.sql.Date;
import java.util.Set;

public interface ShowService {

    Set<Show> findAll();

    Set<Show> findByDate(Date date);

    Show findById(String id);

    Show add(ShowDto showDto);

    Show update(String id, ShowDto showDto);

    void delete(String id);
}
