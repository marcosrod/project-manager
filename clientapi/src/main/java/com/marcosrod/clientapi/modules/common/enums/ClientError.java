package com.marcosrod.clientapi.modules.common.enums;

public enum ClientError {
    DUPLICATED_NAME("This name is already registered.");

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    ClientError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
