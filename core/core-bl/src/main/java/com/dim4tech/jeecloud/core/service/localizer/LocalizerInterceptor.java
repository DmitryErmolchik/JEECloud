package com.dim4tech.jeecloud.core.service.localizer;

import com.dim4tech.jeecloud.core.annotation.Localize;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudException;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudExceptionMessage;
import com.dim4tech.jeecloud.core.service.localizer.LocalizerService;
import com.dim4tech.jeecloud.core.service.sessioninfo.SessionInfoService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.text.MessageFormat;

@Localize
@Interceptor
public class LocalizerInterceptor {
    @Inject
    private LocalizerService localizerService;

    @Inject
    private SessionInfoService sessionInfoService;

    @AroundInvoke
    public Object localizeExceptionMessage(InvocationContext invocationcontext) throws Exception {
        try {
            return invocationcontext.proceed();
        }
        catch (JEECloudException ex) {
            throw new JEECloudException(new JEECloudExceptionMessage(enrichMessage(ex)));
        }
    }

    private String enrichMessage(JEECloudException ex) {
        String localizedMessage = localizeMessage(ex.getExceptionMessage().getMessage());
        return MessageFormat.format(localizedMessage, ex.getArgs().toArray(new String[]{}));
    }

    private String localizeMessage(String message) {
        return localizerService.localize(message, sessionInfoService.getLocale());
    }
}
