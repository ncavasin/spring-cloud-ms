package com.ms.customer.movie.entity.dto;

import lombok.Builder;

import java.time.Duration;
import java.util.Set;

@Builder
public record MovieDto(String id, String title, Double rating, String synopsis, Duration duration,
                       String classification, Set<String> topicIds, Set<String> showIds) {
}
