package com.dim4tech.jeecloud.core.service.configurator;

import com.dim4tech.jeecloud.core.annotation.ConfigurationProperty;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudException;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Properties;

@Startup
@Stateless
public class PropertiesConfiguratorServiceImpl implements ConfiguratorService {

    private final static Logger LOG = LoggerFactory.getLogger(PropertiesConfiguratorServiceImpl.class);
    private Properties properties = new Properties();

    @Inject
    @ConfigurationProperty
    private Instance<String> propertiesLocationInstance;

    @PostConstruct
    private void loadProperties() {
            propertiesLocationInstance.forEach(value -> {
                try {
                    properties.load(this.getClass().getResourceAsStream(value));
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
    }

    @Override
    public String getStringValue(String name) {
        return properties.getProperty(name);
    }

    @Override
    public Integer getIntegerValue(String name) {
        return Integer.parseInt(properties.getProperty(name));
    }

    @Override
    public Double getDoubleValue(String name) {
        return Double.parseDouble(properties.getProperty(name));
    }
}
