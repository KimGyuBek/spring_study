package com.example.testproject.config;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileManager {

    private final Logger LOGGER = Logger.getGlobal();
    private final Environment environment;

    @Autowired
    public ProfileManager(Environment environment) {
        this.environment = environment;
    }

    public void printActiveProfiles() {
        LOGGER.info("[printActiveProfiles] active Profiles size : " + environment.getActiveProfiles().length);
        for(String profile : environment.getActiveProfiles()){
            LOGGER.info("[printActiveProfiles] profile : " + profile);
        }
    }

}
