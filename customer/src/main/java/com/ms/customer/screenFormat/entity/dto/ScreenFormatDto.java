package com.ms.customer.screenFormat.entity.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Set;

@Builder
public record ScreenFormatDto(String id,
                              @NotEmpty String name,
                              @NotEmpty @Positive Double screenHeight,
                              @NotEmpty @Positive Double screenWidth,
                              Set<String> roomIds) {
}
