package com.dim4tech.jeecloud.core.service.localizer;

import com.dim4tech.jeecloud.core.annotation.LocalizationBundle;
import com.dim4tech.jeecloud.core.annotation.LocalizationPath;
import com.dim4tech.jeecloud.core.service.localefinder.LocaleFinderService;
import com.dim4tech.jeecloud.core.service.producer.LocalizationDataProducer;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Stateless
public class LocalizerServiceImpl implements LocalizerService {

    @Inject
    private LocaleFinderService localeFinderService;

    @Inject
    @LocalizationPath(name = "localizationPath")
    private String path;

    @Inject
    @LocalizationBundle(name = "localizationBundl")
    private String bundle;

    private final Map<Locale, ResourceBundle> resourceBundle = new HashMap<>();

    @PostConstruct
    private void initService() {
        for (Locale locale : localeFinderService.getLocales(path, bundle)) {
            resourceBundle.put(locale, ResourceBundle.getBundle(path + "/" + bundle, locale));
        }
    }

    public String localize(String message) {
        return resourceBundle.get(Locale.getDefault()).getString(message);
    }

    @Override
    public String localize(String message, Locale locale) {
        if (resourceBundle.containsKey(locale)) {
            return resourceBundle.get(locale).getString(message);
        }
        else {
            return resourceBundle.get(Locale.getDefault()).getString(message);
        }
    }
}
