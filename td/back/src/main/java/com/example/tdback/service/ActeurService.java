package com.example.tdback.service;

import com.example.tdback.dto.ActeurCreationDto;
import com.example.tdback.dto.ActeurDto;
import com.example.tdback.dto.ActeurMapper;
import com.example.tdback.exception.ActeurNotFoundException;
import com.example.tdback.model.Acteur;
import com.example.tdback.repository.ActeurRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActeurService{
    private final ActeurRepository acteurRepository;
    private final ActeurMapper acteurMapper;

    public ActeurService(ActeurRepository acteurRepository, ActeurMapper acteurMapper ){
        this.acteurRepository = acteurRepository;
        this.acteurMapper = acteurMapper;
    }

    public List<ActeurDto> findAll(){
        return acteurRepository.findAll()
            .stream()
            .map(acteurMapper::toDto)
            .toList();
    }
    public ActeurDto findById(Long id){
        Acteur acteur = acteurRepository.findById(id)
            .orElseThrow(() -> new ActeurNotFoundException(id));
        return acteurMapper.toDto(acteur);
    }
    public ActeurDto create(ActeurCreationDto dto){
        Acteur saved = acteurRepository.save(acteurMapper.toEntity(dto));
        return acteurMapper.toDto(saved);
    }
    public ActeurDto update(Long id, ActeurCreationDto dto){
        Acteur acteur = acteurRepository.findById(id)
             .orElseThrow(() -> new ActeurNotFoundException(id));
        acteur.setNom(dto.nom());
        acteur.setPrenom(dto.prenom());
        return acteurMapper.toDto(acteurRepository.save(acteur));

    }

    public void delete(Long id){
        if(!acteurRepository.existsById(id)){
            throw new ActeurNotFoundException(id);
        }
        acteurRepository.deleteById(id);
    }
}