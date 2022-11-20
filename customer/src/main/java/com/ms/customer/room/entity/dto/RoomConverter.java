package com.ms.customer.room.entity.dto;

import com.ms.customer.room.entity.Room;

import java.util.Set;
import java.util.stream.Collectors;

public class RoomConverter {

    public static Room convert(RoomDto roomDto) {
        return Room.builder()
                .name(roomDto.name())
                .build();
    }

    public static RoomDto convert(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .build();
    }

    public static Set<RoomDto> convert(Set<Room> rooms) {
        return rooms.stream()
                .map(RoomConverter::convert)
                .collect(Collectors.toSet());
    }
}
