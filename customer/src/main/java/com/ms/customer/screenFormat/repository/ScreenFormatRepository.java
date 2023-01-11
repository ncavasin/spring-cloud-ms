package com.ms.customer.screenFormat.repository;

import com.ms.customer.screenFormat.entity.ScreenFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenFormatRepository extends JpaRepository<ScreenFormat, String> {
    @Override
    List<ScreenFormat> findAll();
}
