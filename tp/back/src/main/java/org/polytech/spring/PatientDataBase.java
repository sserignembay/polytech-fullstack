package org.polytech.spring;

public class PatientDataBase implements PatientStore {

    public void save(Patient p) {
        System.out.println("Patient enregistré en base : " + p.getEmail());
    }
}