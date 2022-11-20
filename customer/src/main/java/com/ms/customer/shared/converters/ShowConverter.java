package com.ms.customer.shared.converters;

import com.ms.customer.show.entity.dto.ShowDto;
import com.ms.customer.show.entity.Show;

import java.util.Set;
import java.util.stream.Collectors;

public class ShowConverter {
    public static ShowDto convert(Show show) {
        return ShowDto.builder()
                .date(show.getDate())
                .begin(show.getBegin())
                .end(show.getEnd())
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
