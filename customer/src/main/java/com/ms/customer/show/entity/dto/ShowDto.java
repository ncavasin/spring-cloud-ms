package com.ms.customer.show.entity.dto;

import lombok.Builder;

import java.sql.Date;
import java.sql.Time;

@Builder
public record ShowDto(Date date, Time begin, Time end, String movieId, String roomId) {
}
