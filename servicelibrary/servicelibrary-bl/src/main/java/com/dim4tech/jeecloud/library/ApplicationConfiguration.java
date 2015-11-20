package com.dim4tech.jeecloud.library;

import com.dim4tech.jeecloud.core.annotation.ConfigurationProperty;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class ApplicationConfiguration {
    @Produces
    @ConfigurationProperty
    private String applicationConfiguraton = "/localization.properties";
}
