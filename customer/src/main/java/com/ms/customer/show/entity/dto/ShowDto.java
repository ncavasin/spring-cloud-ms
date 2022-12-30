package com.ms.customer.show.entity.dto;

import lombok.Builder;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Builder
public record ShowDto(String id, Date date, ZonedDateTime beginTime, ZonedDateTime endTime, String movieId, String roomId) {
}
