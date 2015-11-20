package com.dim4tech.jeecloud.core.service.producer;

import com.dim4tech.jeecloud.core.annotation.LocalizationBundle;
import com.dim4tech.jeecloud.core.annotation.LocalizationPath;
import com.dim4tech.jeecloud.core.service.configurator.ConfiguratorService;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Dependent
public class LocalizationDataProducer {
    @Inject
    private ConfiguratorService configuratorService;

    @Produces
    @LocalizationBundle
    public Set<String> getBundle() {
        Set<String> values = new HashSet<>();
        configuratorService.getAllKeys().forEach(key -> {
            values.add(configuratorService.getStringValue(key));
        });
        return values;
    }
}
