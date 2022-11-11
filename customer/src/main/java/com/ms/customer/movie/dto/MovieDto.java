package com.ms.customer.movie.dto;

import lombok.Builder;

import java.sql.Time;

@Builder
public record MovieDto(String id, String title, Double rating, String synopsis, Time duration, String classification) {
}
