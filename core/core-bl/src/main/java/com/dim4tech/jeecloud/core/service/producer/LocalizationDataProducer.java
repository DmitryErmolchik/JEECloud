package com.dim4tech.jeecloud.core.service.producer;

import com.dim4tech.jeecloud.core.annotation.LocalizationBundle;
import com.dim4tech.jeecloud.core.annotation.LocalizationPath;

import javax.enterprise.inject.Produces;

public class LocalizationDataProducer {
    private String path;
    private String bundle;

    @Produces
    @LocalizationPath
    public String getPath() {
        return "i18n";
    }

    @Produces
    @LocalizationBundle
    public String getBundle() {
        return "exception";
    }
}
