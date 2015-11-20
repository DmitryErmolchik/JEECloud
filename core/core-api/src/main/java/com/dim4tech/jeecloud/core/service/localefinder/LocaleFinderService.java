package com.dim4tech.jeecloud.core.service.localefinder;


import java.util.Locale;
import java.util.Set;

public interface LocaleFinderService {
    Set<Locale> getLocales(String bundlePath);
}
