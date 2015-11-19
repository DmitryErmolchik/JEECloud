package com.dim4tech.jeecloud.core.service.configurator;

public interface ConfiguratorService {
    String getStringValue(String name);
    Integer getIntegerValue(String name);
    Double getDoubleValue(String name);
}
