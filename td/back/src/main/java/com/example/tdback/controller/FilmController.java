package com.example.tdback.controller;
import com.example.tdback.model.Film;
import com.example.tdback.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tdback.model.Genre;
import com.example.tdback.dto.FilmDto;
import com.example.tdback.dto.FilmMapper;
import com.example.tdback.dto.FilmCreationDto;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController{
    private final FilmService filmService;
    private final FilmMapper filmMapper;

    public FilmController(FilmService filmService, FilmMapper filmMapper){
        this.filmService = filmService;
        this.filmMapper = filmMapper;
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
    public ResponseEntity<FilmDto> getFilmById(@PathVariable Long id){
        Film film =filmService.getFilmById(id);
        return ResponseEntity.ok(filmMapper.toDto(film));

    }

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmCreationDto creationDto){
        Film film = filmMapper.toEntity(creationDto);
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
}