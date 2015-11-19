package com.dim4tech.jeecloud.library.interceptor;

import com.dim4tech.jeecloud.core.annotation.Localize;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudException;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudExceptionMessage;
import com.dim4tech.jeecloud.core.service.localizer.LocalizerService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

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
            throw new JEECloudException(new JEECloudExceptionMessage(localizerService.localize(ex.getExceptionMessage().getMessage())));
        }
    }
}
