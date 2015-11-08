package com.dim4tech.jeecloud.library.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URL;

public class ServiceDescription {
    private String name;
    private URL url;
    @Min(0)
    @Max(100)
    private int load;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}
