package com.dim4tech.jeecloud.library.service;

import com.dim4tech.jeecloud.core.annotation.Localize;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudException;
import com.dim4tech.jeecloud.library.domain.LibraryExceptionMessage;
import com.dim4tech.jeecloud.library.domain.ServiceDescription;
import com.dim4tech.jeecloud.library.interceptor.LocalizationInterceptor;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import java.net.URL;
import java.util.*;

@Singleton
public class LibraryServiceSingletoneImpl implements LibraryService {

    private Map<String, Map<URL, ServiceDescription>> serviceDescriptionMap = new HashMap<>();

    @Localize
    @Override
    public void add(ServiceDescription serviceDescription) {
        if (!serviceDescriptionMap.containsKey(serviceDescription.getName())) {
            Map<URL, ServiceDescription> map = new HashMap<>();
            map.put(serviceDescription.getUrl(), serviceDescription);
            serviceDescriptionMap.put(serviceDescription.getName(), map);
        }
        else {
            if (!serviceDescriptionMap.get(serviceDescription.getName()).containsKey(serviceDescription.getUrl())) {
                serviceDescriptionMap.get(serviceDescription.getName()).put(serviceDescription.getUrl(), serviceDescription);
            }
            else {
                throw new JEECloudException(LibraryExceptionMessage.SERVICE_ALREADY_EXISTS, serviceDescription.getName(), serviceDescription.getUrl());
            }
        }
    }

    @Override
    public void update(ServiceDescription serviceDescription) {
            serviceDescriptionMap.get(serviceDescription.getName()).put(serviceDescription.getUrl(), serviceDescription);
    }

    @Override
    public void delete(String name, URL url) {
        if (serviceDescriptionMap.containsKey(name)) {
            serviceDescriptionMap.get(name).remove(url);
            if (serviceDescriptionMap.get(name).values().isEmpty()) {
                serviceDescriptionMap.remove(name);
            }
        }
    }

    @Override
    public void delete(ServiceDescription serviceDescription) {
        delete(serviceDescription.getName(), serviceDescription.getUrl());
    }

    @Localize
    @Override
    @Interceptors(LocalizationInterceptor.class)
    public ServiceDescription get(String name, URL url) {
        try {
            return serviceDescriptionMap.get(name).get(url);
        }
        catch (NullPointerException ex) {
            throw new JEECloudException(LibraryExceptionMessage.SERVICE_NOT_EXISTS, name);
        }
    }

    @Override
    public ServiceDescription getServiceDescriptionWithMinimalLoad(String name) {
        ServiceDescription result = null;
        int minLoad = 100;
        for (ServiceDescription serviceDescription : serviceDescriptionMap.get(name).values()) {
            if (minLoad >= serviceDescription.getLoad()) {
                minLoad = serviceDescription.getLoad();
                result = serviceDescription;
            }
        }
        return result;
    }

    @Override
    public Set<String> getAvailableServices() {
        return serviceDescriptionMap.keySet();
    }
}
