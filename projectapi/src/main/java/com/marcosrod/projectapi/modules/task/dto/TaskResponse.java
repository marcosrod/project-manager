package com.marcosrod.projectapi.modules.task.dto;

public record TaskResponse(Long id, String name, String description, String status, Long projectId,
                           String projectName) {
}
