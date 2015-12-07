package com.dim4tech.jeecloud.core.service.sessioninfo;

import java.util.Locale;

public interface SessionInfoService {
    void setLocale(String lang);
    Locale getLocale();
}
