package com.ms.customer.show.entity.dto;

import com.ms.customer.show.entity.Show;

import java.util.Set;
import java.util.stream.Collectors;

public class ShowConverter {
    public static ShowDto convert(Show show) {
        return ShowDto.builder()
                .id(show.getId())
                .date(show.getDate())
                .beginTime(show.getBeginTime())
                .endTime(show.getEndTime())
                .movieId(show.getMovie().getId())
                .roomId(show.getRoom().getId())
                .build();
    }

    public static Set<ShowDto> convert(Set<Show> shows) {
        return shows.stream()
                .map(ShowConverter::convert)
                .collect(Collectors.toSet());
    }
}
