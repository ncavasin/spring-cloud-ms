package com.ms.customer.show.repository;

import com.ms.customer.show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, String> {
    @Override
    List<Show> findAll();

    List<Show> findByDate(Date date);
}
