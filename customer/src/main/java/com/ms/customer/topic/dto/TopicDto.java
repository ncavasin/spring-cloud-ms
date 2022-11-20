package com.ms.customer.topic.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record TopicDto(String id, String name, Set<String> movieIds) {
}
