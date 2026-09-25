package org.polytech.spring;

public class Patient {
    private String prenom;
    private String nom;
    private String email;

    public Patient(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }
}