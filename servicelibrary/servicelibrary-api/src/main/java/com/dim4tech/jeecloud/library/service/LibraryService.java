package com.dim4tech.jeecloud.library.service;

import com.dim4tech.jeecloud.library.domain.ServiceDescription;

import java.net.URL;
import java.util.Set;

public interface LibraryService {
    void add(ServiceDescription serviceDescription);
    void delete(String name, URL url);
    void delete(ServiceDescription serviceDescription);
    ServiceDescription get(String name, URL url);
    ServiceDescription getServiceDescriptionWithMinimalLoad(String name);
    Set<String> getAvailableServices();
}
