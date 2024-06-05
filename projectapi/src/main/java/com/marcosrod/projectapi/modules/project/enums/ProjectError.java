package com.marcosrod.projectapi.modules.project.enums;

public enum ProjectError {

    DUPLICATED_NAME("This project name is already registered."),
    ID_NOT_FOUND("This project id doesn't exists."),
    INVALID_CLIENT("The project creation needs a valid Client ID.");

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    ProjectError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
