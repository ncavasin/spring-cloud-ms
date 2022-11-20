package com.ms.customer.shared.converters;

import com.ms.customer.room.entity.Room;
import com.ms.customer.room.entity.dto.RoomDto;

import java.util.Set;
import java.util.stream.Collectors;

public class RoomConverter {

    public static RoomDto convert(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .branchId(room.getId())
                .build();
    }

    public static Set<RoomDto> convert(Set<Room> rooms) {
        return rooms.stream()
                .map(RoomConverter::convert)
                .collect(Collectors.toSet());
    }
}
