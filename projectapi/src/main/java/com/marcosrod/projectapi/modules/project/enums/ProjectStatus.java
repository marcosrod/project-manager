package com.marcosrod.projectapi.modules.project.enums;

public enum ProjectStatus {
    O("Open"),
    C("Closed");

    private String description;

    public String getDescription() {
        return description;
    }

    ProjectStatus(String description) {
        this.description = description;
    }
}
