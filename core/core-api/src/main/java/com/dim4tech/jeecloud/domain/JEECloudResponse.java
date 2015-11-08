package com.dim4tech.jeecloud.domain;

public class JEECloudResponse<T> {
    private Error error;
    private T response;

    public JEECloudResponse(Error error) {
        this.error = error;
    }

    public JEECloudResponse(T response) {
        this.response = response;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
