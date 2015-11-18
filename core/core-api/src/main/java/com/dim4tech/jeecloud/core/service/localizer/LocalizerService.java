package com.dim4tech.jeecloud.core.service.localizer;

import java.util.Locale;

public interface LocalizerService {
    String localize(String message);
    String localize(String message, Locale locale);
}
