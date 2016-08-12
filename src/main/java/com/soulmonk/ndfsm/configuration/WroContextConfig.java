package com.soulmonk.ndfsm.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ro.isdc.wro.http.ConfigurableWroFilter;

import java.io.IOException;
import java.util.Properties;

/**
 * ndfsm
 * Created by SoulMonk on 12.08.2016.
 */
@Configuration
public class WroContextConfig {

    private static final Logger logger = LoggerFactory.getLogger(WroContextConfig.class);

    @Bean(name = "wroFilter")
    public ConfigurableWroFilter wroFilter() {
        ConfigurableWroFilter filter = new ConfigurableWroFilter();
        filter.setProperties(wroProperties());
        return filter;
    }

    @Bean(name = "wroProperties")
    public Properties wroProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("wro.properties"));
        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();
        } catch (IOException e) {
            logger.warn("Cannot load properties file.");
        }
        return properties;
    }
}
