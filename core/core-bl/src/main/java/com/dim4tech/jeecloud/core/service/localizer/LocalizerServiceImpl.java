package com.dim4tech.jeecloud.core.service.localizer;

import com.dim4tech.jeecloud.core.annotation.Localization;
import com.dim4tech.jeecloud.core.service.localefinder.LocaleFinderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Stateless
public class LocalizerServiceImpl implements LocalizerService {

    @Inject
    private LocaleFinderService localeFinderService;

    private final Map<Locale, ResourceBundle> resourceBundle = new HashMap<>();

    public LocalizerServiceImpl() {
        String path = this.getClass().getAnnotation(Localization.class).path();
        String bundle = this.getClass().getAnnotation(Localization.class).bundle();
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
