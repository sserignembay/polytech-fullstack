package com.example.tdback.controller;
import com.example.tdback.model.Film;
import com.example.tdback.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tdback.model.Genre;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController{
    private final FilmService filmService;

    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }
    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms(
        @RequestParam(required = false) String realisateur,
        @RequestParam(required = false) Genre genre){
      return ResponseEntity.ok(filmService.getAllFilms(realisateur, genre));

    }
       
    

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id){
        return ResponseEntity.ok(filmService.getFilmById(id));

    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film){
        Film createdFilm =filmService.createFilm(film);
        URI location = URI.create("/films/" + createdFilm.getId());
        return ResponseEntity.created(location).body(createdFilm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film){
        return ResponseEntity.ok(filmService.updateFilm(id, film));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id){
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}