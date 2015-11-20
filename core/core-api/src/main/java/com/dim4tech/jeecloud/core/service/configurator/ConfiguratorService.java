package com.dim4tech.jeecloud.core.service.configurator;

import java.util.Set;

public interface ConfiguratorService {
    Set<String> getAllKeys();
    String getStringValue(String key);
    Integer getIntegerValue(String key);
    Double getDoubleValue(String key);
}
