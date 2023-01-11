package com.ms.customer.room.repository;

import com.ms.customer.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    @Override
    List<Room> findAll();
}
