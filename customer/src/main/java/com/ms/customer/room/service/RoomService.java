package com.ms.customer.room.service;

import com.ms.customer.room.entity.Room;
import com.ms.customer.room.entity.dto.RoomDto;

import java.util.Set;

public interface RoomService {

    Set<Room> findAll();

    Set<Room> findAllById(Set<String> ids);

    Room findById(String id);

    Room add(RoomDto roomDto);

    Room update(String id, RoomDto roomDto);

    void delete(String id);
}
