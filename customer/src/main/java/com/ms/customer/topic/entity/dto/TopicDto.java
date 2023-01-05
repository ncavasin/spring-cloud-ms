package com.ms.customer.topic.entity.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Builder
public record TopicDto(String id,
                       @NotEmpty String name,
                       @Nullable Set<String> movieIds) {
}
