package com.dim4tech.jeecloud.core.domain;

public enum SessionInfoParam {
    LANGUAGE(Names.LANGUAGE, DefaultValues.LANGUAGE);

    private String name;
    private String defaultValue;

    SessionInfoParam(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public static class Names {
        public static final String LANGUAGE = "lang";
    }

    public static class DefaultValues {
        public static final String LANGUAGE = "en";
    }
}
