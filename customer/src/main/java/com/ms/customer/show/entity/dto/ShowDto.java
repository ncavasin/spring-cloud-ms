package com.ms.customer.show.entity.dto;

import lombok.Builder;

import java.sql.Date;
import java.sql.Time;

@Builder
public record ShowDto(String id, Date date, Time beginTime, Time endTime, String movieId, String roomId) {
}
