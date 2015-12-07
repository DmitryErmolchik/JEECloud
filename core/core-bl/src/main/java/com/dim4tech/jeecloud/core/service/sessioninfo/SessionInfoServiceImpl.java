package com.dim4tech.jeecloud.core.service.sessioninfo;

import javax.enterprise.context.RequestScoped;
import java.util.Locale;

@RequestScoped
public class SessionInfoServiceImpl implements SessionInfoService {

    private Locale locale;

    @Override
    public void setLocale(String lang) {
        this.locale = new Locale(lang);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
