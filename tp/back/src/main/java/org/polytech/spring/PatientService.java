package org.polytech.spring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PatientService {

    private final PatientStore store;

    public PatientService(PatientStore store) {
        this.store = store;
    }

    public void savePatient(Patient p) {
        store.save(p);
    }

    @PostConstruct
    public void init() {
        System.out.println("PatientService : @PostConstruct");
    }

    @PreDestroy
    public void close() {
        System.out.println("PatientService : @PreDestroy");
    }
}