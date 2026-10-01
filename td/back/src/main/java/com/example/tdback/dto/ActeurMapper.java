package com.example.tdback.dto;

import com.example.tdback.model.Acteur;
import org.springframework.stereotype.Component;

@Component
public class ActeurMapper{
    public ActeurDto toDto(Acteur acteur){
        return new ActeurDto(
            acteur.getId(),
            acteur.getNom(),
            acteur.getPrenom()
        );

    }

    public Acteur toEntity(ActeurCreationDto dto){
            Acteur acteur = new Acteur();
            acteur.setNom(dto.nom());
            acteur.setPrenom(dto.prenom());
            return acteur;

    }
}