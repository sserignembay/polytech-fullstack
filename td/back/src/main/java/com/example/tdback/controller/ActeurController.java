package com.example.tdback.controller;

import com.example.tdback.dto.ActeurCreationDto;
import com.example.tdback.dto.ActeurDto;
import com.example.tdback.service.ActeurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tdback.dto.FilmDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController{
    private final ActeurService acteurService;
    public ActeurController(ActeurService acteurService){
        this.acteurService = acteurService;
    }

    @GetMapping
    public ResponseEntity<List<ActeurDto>> getAllActeurs(){
        return ResponseEntity.ok(acteurService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActeurDto> getActeurById(@PathVariable Long id){
        return ResponseEntity.ok(acteurService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActeurDto> createActeur(@RequestBody ActeurCreationDto creationDto){
        ActeurDto created = acteurService.create(creationDto);
        URI location = URI.create("/acteurs/" + created.id());
        return ResponseEntity.created(location).body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ActeurDto> updateActeur(@PathVariable Long id, @RequestBody ActeurCreationDto creationDto){
        return ResponseEntity.ok(acteurService.update(id, creationDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActeur(@PathVariable Long id){
        acteurService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<FilmDto>> getFilmsByActeur(@PathVariable Long id){
        return ResponseEntity.ok(acteurService.findFilmsByActeur(id));
        
    }
}