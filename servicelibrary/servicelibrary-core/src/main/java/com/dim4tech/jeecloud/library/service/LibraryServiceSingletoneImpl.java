package com.dim4tech.jeecloud.library.service;

import com.dim4tech.jeecloud.library.domain.ServiceDescription;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.net.URL;
import java.util.*;

@Startup
@Singleton
public class LibraryServiceSingletoneImpl implements LibraryService {

    private Map<String, Map<URL, ServiceDescription>> serviceDescriptionMap = new HashMap<>();

    @Override
    public void add(ServiceDescription serviceDescription) {
        if (!serviceDescriptionMap.containsKey(serviceDescription.getName())) {
            Map<URL, ServiceDescription> map = new HashMap<>();
            map.put(serviceDescription.getUrl(), serviceDescription);
            serviceDescriptionMap.put(serviceDescription.getName(), map);
        }
        else {
            serviceDescriptionMap.get(serviceDescription.getName()).put(serviceDescription.getUrl(), serviceDescription);
        }
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

    @Override
    public ServiceDescription get(String name, URL url) {
        return serviceDescriptionMap.get(name).get(url);
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
