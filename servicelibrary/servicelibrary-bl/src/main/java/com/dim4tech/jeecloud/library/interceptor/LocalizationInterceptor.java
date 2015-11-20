package com.dim4tech.jeecloud.library.interceptor;

import com.dim4tech.jeecloud.core.annotation.Localize;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudException;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudExceptionMessage;
import com.dim4tech.jeecloud.core.service.localizer.LocalizerService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.text.MessageFormat;

@Interceptor
@Localize
public class LocalizationInterceptor {
    @Inject
    private LocalizerService localizerService;

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
        return MessageFormat.format(localizedMessage, ex.getArgs());
    }

    private String localizeMessage(String message) {
        return localizerService.localize(message);
    }
}
