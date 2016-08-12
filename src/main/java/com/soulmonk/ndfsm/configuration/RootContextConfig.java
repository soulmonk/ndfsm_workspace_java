package com.soulmonk.ndfsm.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.annotation.PostConstruct;

/**
 * ndfsm
 * Created by SoulMonk on 12.08.2016.
 */
@Configuration
@Import({
        DatasourceConfig.class,
        SecurityConfig.class,
        WroContextConfig.class
})
@ComponentScan(value = {
        "com.soulmonk.ndfsm.service.jpa",
})
public class RootContextConfig {

    private static final Logger logger = LoggerFactory.getLogger(RootContextConfig.class);

    @Autowired
    private Environment env;

    @PostConstruct
    public void initApp() {
        logger.debug("Looking for Spring profiles...");
        if (env.getActiveProfiles().length == 0) {
            logger.info("No Spring profile configured, running with default configuration.");
        } else {
            for (String profile : env.getActiveProfiles()) {
                logger.info("Detected Spring profile: {}", profile);
            }
        }
    }
}
