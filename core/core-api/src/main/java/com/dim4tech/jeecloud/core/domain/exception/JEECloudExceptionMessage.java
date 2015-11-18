package com.dim4tech.jeecloud.core.domain.exception;

public class JEECloudExceptionMessage {
    private final String message;

    public JEECloudExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
