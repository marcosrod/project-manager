package com.marcosrod.projectapi.modules.task.enums;

public enum TaskError {

    DUPLICATED_NAME("This task name is already registered.");

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    TaskError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
