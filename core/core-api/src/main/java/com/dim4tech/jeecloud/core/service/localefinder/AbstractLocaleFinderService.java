package com.dim4tech.jeecloud.core.service.localefinder;

import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractLocaleFinderService implements LocaleFinderService {
    protected Pattern localePattern = Pattern.compile("\\_([a-z]{2})(\\_([A-Z]{2}))*");
    protected Matcher matcher;

    @Override
    abstract public Set<Locale> getLocales(String path, String bundle);

    protected Locale getLocale(String value) {
        matcher = localePattern.matcher(value);
        if (matcher.find()) {
            if (isFullLocaleSpecification()) {
                return new Locale(getLanguage(), getCountry());
            }
            else {
                return new Locale(getLanguage());
            }
        }
        return Locale.getDefault();
    }

    protected boolean isFullLocaleSpecification() {
        return matcher.groupCount() == 3 && matcher.group(3) != null;
    }

    protected String getLanguage() {
        return matcher.group(1);
    }

    protected String getCountry() {
        return matcher.group(3);
    }
}
