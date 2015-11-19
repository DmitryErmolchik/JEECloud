package com.dim4tech.jeecloud.core.service.configurator;

import com.dim4tech.jeecloud.core.domain.exception.JEECloudException;
import com.dim4tech.jeecloud.core.domain.exception.JEECloudExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

@Stateless
public class PropertiesConfiguratorServiceImpl implements ConfiguratorService {

    private final static Logger LOG = LoggerFactory.getLogger(PropertiesConfiguratorServiceImpl.class);
    private Properties properties = new Properties();

    @PostConstruct
    private void loadProperties() {
        try {
            properties.load(this.getClass().getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new JEECloudException(new JEECloudExceptionMessage(e.getMessage()));
        }
    }

    private File getFileFromUrl(URL url) {
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return new File(url.getPath());
        }
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
