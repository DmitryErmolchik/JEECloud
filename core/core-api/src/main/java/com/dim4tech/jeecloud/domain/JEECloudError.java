package com.dim4tech.jeecloud.domain;

public class JEECloudError {
    private String message;

    public JEECloudError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
