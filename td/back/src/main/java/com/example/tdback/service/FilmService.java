package com.example.tdback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tdback.exception.FilmNotFoundException;
import com.example.tdback.model.Film;
import com.example.tdback.repository.FilmRepository;
import com.example.tdback.model.Genre;
@Service
public class FilmService{
    private final FilmRepository filmRepository;
    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }


    public Film getFilmById(Long id){
        Film film = filmRepository.findById(id);
        if(film == null){
             throw new FilmNotFoundException(id);
        }
        return film;
    }
    

    public Film createFilm(Film film){
        film.setId(null);
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film){
        Film existingFilm = getFilmById(id);
        existingFilm.setTitre(film.getTitre());
        existingFilm.setRealisateur(film.getRealisateur());
        existingFilm.setDateSortie(film.getDateSortie());
        existingFilm.setGenre(film.getGenre());
        return filmRepository.save(existingFilm);
    }
    public void deleteFilm(Long id){
        getFilmById(id);
        filmRepository.deleteById(id);

    }
     public List<Film> getAllFilms(String realisateur, Genre genre){
        return filmRepository.findAll().stream()
            .filter(film -> realisateur == null || film.getRealisateur().equalsIgnoreCase(realisateur))
            .filter(film -> genre == null || film.getGenre() == genre)
            .toList();
    }
}
