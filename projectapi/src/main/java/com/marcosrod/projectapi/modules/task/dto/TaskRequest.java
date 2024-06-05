package com.marcosrod.projectapi.modules.task.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(@NotEmpty String name, @NotEmpty String description, @NotNull Long projectId) {
}
