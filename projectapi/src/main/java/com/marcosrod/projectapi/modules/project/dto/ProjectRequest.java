package com.marcosrod.projectapi.modules.project.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProjectRequest(@NotEmpty String name, @NotEmpty String description, @NotNull Long clientId) {
}
