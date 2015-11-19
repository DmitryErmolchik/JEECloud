package com.dim4tech.jeecloud.core.service.producer;

import com.dim4tech.jeecloud.core.annotation.LocalizationBundle;
import com.dim4tech.jeecloud.core.annotation.LocalizationPath;
import com.dim4tech.jeecloud.core.service.configurator.ConfiguratorService;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

@Dependent
public class LocalizationDataProducer {
    @Inject
    private ConfiguratorService configuratorService;

    @Produces
    @LocalizationPath
    public String getPath(InjectionPoint injectionPoint) {
        String variableName = injectionPoint.getMember().getName();
        String name = injectionPoint.getAnnotated().getAnnotation(LocalizationPath.class).name();
        return !name.equals("") ? configuratorService.getStringValue(name) : configuratorService.getStringValue(variableName);
    }

    @Produces
    @LocalizationBundle
    public String getBundle(InjectionPoint injectionPoint) {
        String variableName = injectionPoint.getMember().getName();
        String name = injectionPoint.getAnnotated().getAnnotation(LocalizationPath.class).name();
        return !name.equals("") ? configuratorService.getStringValue(name) : configuratorService.getStringValue(variableName);
    }
}
