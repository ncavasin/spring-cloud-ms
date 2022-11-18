package com.ms.customer.movie.dto;

import com.ms.customer.topic.Topic;
import lombok.Builder;

import java.sql.Time;
import java.util.Set;

@Builder
public record MovieDto(String id, String title, Double rating, String synopsis, Time duration, String classification,
                       Set<Topic> topics) {
}
