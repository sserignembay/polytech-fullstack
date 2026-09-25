package org.polytech.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PatientStore databaseStore() {
        return new PatientDataBase();
    }

    @Bean
    public PatientService patientService(PatientStore store) {
        return new PatientService(store);
    }
}