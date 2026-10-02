package com.example.tdback.controller;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tdback.dto.ActeurDto;
import com.example.tdback.dto.FilmCreationDto;
import com.example.tdback.dto.FilmDetailDto;
import com.example.tdback.dto.FilmDto;
import com.example.tdback.dto.FilmMapper;
import com.example.tdback.model.Film;
import com.example.tdback.model.Genre;
import com.example.tdback.service.ActeurService;
import com.example.tdback.service.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController{
    private final FilmService filmService;
    private final FilmMapper filmMapper;
    private final ActeurService acteurService;

   public FilmController(FilmService filmService, FilmMapper filmMapper, ActeurService acteurService){
    this.filmService = filmService;
    this.filmMapper = filmMapper;
    this.acteurService = acteurService;
}
    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilms(
        @RequestParam(required = false) String realisateur,
        @RequestParam(required = false) Genre genre){
            List<FilmDto> dtos = filmService.getAllFilms(realisateur, genre)
            .stream()
            .map(filmMapper::toDto)
            .toList();
      return ResponseEntity.ok(dtos);

    }
       
    

    @GetMapping("/{id}")
public ResponseEntity<FilmDetailDto> getFilmById(@PathVariable Long id){
    return ResponseEntity.ok(filmService.getFilmDetail(id));
}

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmCreationDto creationDto){
        Film film = filmMapper.toEntity(creationDto);    // DTO reçu → entité
        Film createdFilm =filmService.createFilm(film);
        FilmDto dto = filmMapper.toDto(createdFilm);
        URI location = URI.create("/films/" + createdFilm.getId());
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDto> updateFilm(@PathVariable Long id, @RequestBody FilmCreationDto creationDto ){
        Film film = filmMapper.toEntity(creationDto);
        Film updateFilm = filmService.updateFilm(id, film);
        FilmDto dto = filmMapper.toDto(updateFilm);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id){
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping ("/{id}/acteurs/{acteurId}")
    public ResponseEntity<Void> ajouterActeur(@PathVariable Long id, @PathVariable Long acteurId){
        filmService.ajouterActeur(id, acteurId);
        return ResponseEntity.noContent().build();
    }
     @DeleteMapping ("/{id}/acteurs/{acteurId}")
    public ResponseEntity<Void> retirerActeur(@PathVariable Long id, @PathVariable Long acteurId){
        filmService.retirerActeur(id, acteurId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/acteurs")
    public ResponseEntity<List<ActeurDto>> getActeursByFilm(@PathVariable Long id){
    return ResponseEntity.ok(acteurService.findActeursByFilm(id));
}

}