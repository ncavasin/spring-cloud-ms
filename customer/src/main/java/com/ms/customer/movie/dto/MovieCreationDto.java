package com.ms.customer.movie.dto;

import java.sql.Time;

public record MovieCreationDto(String title, Double rating, String synopsis, Time duration, String classification) {
}
