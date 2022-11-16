package com.ms.customer.room.dto;

import com.ms.customer.room.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomConverter {

    public static Room convert (RoomDto roomDto){
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

    public static List<RoomDto> convert(List<Room> rooms) {
        return rooms.stream()
                .map(RoomConverter::convert)
                .collect(Collectors.toList());
    }
}
