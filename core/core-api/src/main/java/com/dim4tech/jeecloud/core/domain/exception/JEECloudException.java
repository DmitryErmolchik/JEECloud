package com.dim4tech.jeecloud.core.domain.exception;

import java.util.ArrayList;
import java.util.List;

public class JEECloudException extends RuntimeException{

    private JEECloudExceptionMessage exceptionMessage;
    private List<String> args;

    public JEECloudException(JEECloudExceptionMessage exceptionMessage, Object... args) {
        this.exceptionMessage = exceptionMessage;
        this.args = new ArrayList<>(args.length);
        for (Object arg : args) {
            this.args.add(arg.toString());
        }
    }

    public JEECloudExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

    public List<String> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return exceptionMessage.getMessage();
    }
}
