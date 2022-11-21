package com.ms.customer.screenFormat.service;

import com.ms.customer.screenFormat.entity.ScreenFormat;
import com.ms.customer.screenFormat.entity.dto.ScreenFormatDto;

import java.util.Set;

public interface ScreenFormatService {

    Set<ScreenFormat> findAll();

    ScreenFormat findById(String id);

    ScreenFormat add(ScreenFormatDto screenFormatDto);

    ScreenFormat update(String id, ScreenFormatDto screenFormatDto);

    void delete(String id);
}
