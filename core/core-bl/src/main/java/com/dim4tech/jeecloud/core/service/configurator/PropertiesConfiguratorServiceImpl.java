package com.dim4tech.jeecloud.core.service.configurator;

import com.dim4tech.jeecloud.core.annotation.ConfigurationProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

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
    public Set<String> getAllKeys() {
        return properties.stringPropertyNames();
    }

    @Override
    public String getStringValue(String key) {
        return properties.getProperty(key);
    }

    @Override
    public Integer getIntegerValue(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    @Override
    public Double getDoubleValue(String key) {
        return Double.parseDouble(properties.getProperty(key));
    }
}
