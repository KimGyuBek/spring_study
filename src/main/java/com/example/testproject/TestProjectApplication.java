package com.example.testproject;

import com.example.testproject.config.ProfileManager;
import com.example.testproject.config.env.EnvConfiguration;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaAuditing//jpa 관련 기능 bean들이 올라올 수 있도록 한다.
public class TestProjectApplication {

    private final Logger LOGGER = Logger.getGlobal();

    @Autowired
    public TestProjectApplication(EnvConfiguration envConfiguration, ProfileManager profileManager) {
        LOGGER.info(envConfiguration.getMessage());
        profileManager.printActiveProfiles();
    }
    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);
    }

}
