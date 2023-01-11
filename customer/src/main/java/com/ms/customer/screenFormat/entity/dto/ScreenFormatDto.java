package com.ms.customer.screenFormat.entity.dto;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Builder
public record ScreenFormatDto(String id,
                              @NotEmpty String name,
                              @NotNull @Positive Double screenHeight,
                              @NotNull @Positive Double screenWidth,
                              Set<String> roomIds) {
}
