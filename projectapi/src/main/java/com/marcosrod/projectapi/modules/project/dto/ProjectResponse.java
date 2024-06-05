package com.marcosrod.projectapi.modules.project.dto;

public record ProjectResponse(Long id, String name, String description, String status,
                              Long clientId) {
}
