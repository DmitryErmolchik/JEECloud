package com.dim4tech.jeecloud.library;

import com.dim4tech.jeecloud.core.annotation.ConfigurationProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import java.util.Locale;

@Dependent
public class ApplicationConfiguration {
    @Produces
    @ConfigurationProperty
    private String applicationConfiguraton = "/localization.properties";

    @PostConstruct
    private void init() {
        Locale.setDefault(Locale.ENGLISH);
    }
}
