package com.ms.customer.topic.entity.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record TopicDto(String id, String name, Set<String> movieIds) {
}
