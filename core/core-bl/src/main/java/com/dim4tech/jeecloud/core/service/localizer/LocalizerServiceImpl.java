package com.dim4tech.jeecloud.core.service.localizer;

import com.dim4tech.jeecloud.core.annotation.LocalizationBundle;
import com.dim4tech.jeecloud.core.service.localefinder.LocaleFinderService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Dependent
public class LocalizerServiceImpl implements LocalizerService {

    @Inject
    private LocaleFinderService localeFinderService;

    @Inject
    @LocalizationBundle
    private Set<String> bundlePath;

    private final Map<Locale, ResourceBundle> resourceBundle = new HashMap<>();

    @PostConstruct
    private void initService() {
        bundlePath.forEach(value -> {
            for (Locale locale : localeFinderService.getLocales(value)) {
                resourceBundle.put(locale, ResourceBundle.getBundle(value, locale));
            }
        });
    }

    public String localize(String message) {
        try {
            return new String(new String(resourceBundle.get(Locale.getDefault()).getString(message).getBytes("ISO-8859-1")).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return resourceBundle.get(Locale.getDefault()).getString(message);
        }
    }

    @Override
    public String localize(String message, Locale locale) {
        if (resourceBundle.containsKey(locale)) {
            try {
                return new String(new String(resourceBundle.get(locale).getString(message).getBytes("ISO-8859-1")).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return resourceBundle.get(locale).getString(message);
            }
        }
        else {
            return localize(message);
        }
    }
}
