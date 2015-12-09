package com.dim4tech.jeecloud.core.domain;

import javax.ws.rs.core.MediaType;

public class MediaTypeUtf8 {
    public final static String APPLICATION_JSON = MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8";
    public final static String APPLICATION_XML = MediaType.APPLICATION_XML + ";" + MediaType.CHARSET_PARAMETER + "=UTF-8";
}
