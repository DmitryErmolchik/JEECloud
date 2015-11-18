package com.dim4tech.jeecloud.core.domain;

public class JEECloudResponse<T> {
    private T response;
    private JEECloudError error;

    public JEECloudResponse(T response, JEECloudError error) {
        this.response = response;
        this.error = error;
    }

    public JEECloudError getError() {
        return error;
    }

    public void setError(JEECloudError error) {
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
