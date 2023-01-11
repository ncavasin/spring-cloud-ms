package com.ms.customer.screenFormat.entity.dto;

import com.ms.customer.screenFormat.entity.ScreenFormat;
import com.ms.customer.shared.entities.AbstractEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class ScreenFormatConverter {

    public static ScreenFormatDto convert(ScreenFormat screenFormat) {
        Set<String> rooms = screenFormat.getRooms() == null ? null : screenFormat.getRooms()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toSet());
        return ScreenFormatDto.builder()
                .id(screenFormat.getId())
                .screenHeight(screenFormat.getScreenHeight())
                .screenWidth(screenFormat.getScreenWidth())
                .name(screenFormat.getName())
                .roomIds(rooms)
                .build();
    }

    public static Set<ScreenFormatDto> convert(Set<ScreenFormat> screenFormats) {
        return screenFormats.stream()
                .map(ScreenFormatConverter::convert)
                .collect(Collectors.toSet());
    }

    public static ScreenFormat convert(ScreenFormatDto screenFormatDto) {
        return ScreenFormat.builder()
                .name(screenFormatDto.name())
                .screenHeight(screenFormatDto.screenHeight())
                .screenWidth(screenFormatDto.screenWidth())
                .build();
    }
}
