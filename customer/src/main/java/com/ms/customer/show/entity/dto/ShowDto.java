package com.ms.customer.show.entity.dto;

import lombok.Builder;

import java.util.Date;
import java.time.LocalTime;


@Builder
public record ShowDto(String id, Date date, LocalTime beginTime, LocalTime endTime, String movieId, String roomId) {
}
