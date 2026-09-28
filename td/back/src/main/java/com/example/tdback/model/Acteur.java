package com.example.tdback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Acteur{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nom;

    private String prenom;
    public Acteur(){

    }
    public Acteur(Long id, String nom, String prenom){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

     public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

       public String getPrenom(){
        return prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }


    
}
    


