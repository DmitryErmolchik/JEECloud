package com.dim4tech.jeecloud.core.domain.exception;

public class JEECloudException extends RuntimeException{

    private JEECloudExceptionMessage exceptionMessage;

    public JEECloudException(JEECloudExceptionMessage exceptionMessage, Object... objects) {
        super(exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public JEECloudExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
