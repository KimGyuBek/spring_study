package com.example.testproject.config.env;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfiguration implements EnvConfiguration{

    private final Logger LOGGER = Logger.getGlobal();

    @Value("${test.project.loading.message}")
    private String message;


    @Override
    @Bean
    public String getMessage() {
        LOGGER.info("[getMessage] devConfiguration.");
        return message;
    }
}
