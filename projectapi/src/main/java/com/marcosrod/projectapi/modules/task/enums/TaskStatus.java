package com.marcosrod.projectapi.modules.task.enums;

public enum TaskStatus {

    C("Created"),
    D("Developing"),
    F("Finished");

    private String description;

    public String getDescription() {
        return description;
    }

    TaskStatus(String description) {
        this.description = description;
    }
}
