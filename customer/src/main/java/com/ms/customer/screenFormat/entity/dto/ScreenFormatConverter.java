package com.ms.customer.screenFormat.entity.dto;

import com.ms.customer.screenFormat.entity.ScreenFormat;

import java.util.Set;
import java.util.stream.Collectors;

public class ScreenFormatConverter {

    public static ScreenFormatDto convert(ScreenFormat screenFormat) {
        return ScreenFormatDto.builder()
                .id(screenFormat.getId())
                .name(screenFormat.getName())
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
