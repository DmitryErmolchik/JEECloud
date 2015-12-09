package com.dim4tech.jeecloud.library.domain;

import com.dim4tech.jeecloud.core.domain.exception.JEECloudExceptionMessage;

public class LibraryExceptionMessage {
    public final static JEECloudExceptionMessage SERVICE_ALREADY_EXISTS = new JEECloudExceptionMessage("jeecloud.exception.service.alreadyExists");
    public final static JEECloudExceptionMessage SERVICE_WITH_NAME_NOT_EXISTS = new JEECloudExceptionMessage("jeecloud.exception.service.notExistsByName");
    public final static JEECloudExceptionMessage SERVICE_WITH_NAME_AND_URL_NOT_EXISTS = new JEECloudExceptionMessage("jeecloud.exception.service.notExistsByNameAndUrl");
}
