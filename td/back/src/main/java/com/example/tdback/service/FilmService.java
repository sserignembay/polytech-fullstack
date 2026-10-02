package com.example.tdback.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tdback.exception.ActeurNotFoundException;
import com.example.tdback.exception.FilmNotFoundException;
import com.example.tdback.model.Acteur;
import com.example.tdback.model.Film;
import com.example.tdback.model.Genre;
import com.example.tdback.repository.ActeurRepository;
import com.example.tdback.repository.FilmRepository;
import com.example.tdback.dto.FilmDetailDto;
import com.example.tdback.dto.FilmMapper;

@Service
public class FilmService{
    private final FilmRepository filmRepository;
    private final ActeurRepository acteurRepository;
    private final FilmMapper filmMapper;
    
         public FilmService(FilmRepository filmRepository, ActeurRepository acteurRepository, FilmMapper filmMapper){
        this.filmRepository = filmRepository;
        this.acteurRepository = acteurRepository;
        this.filmMapper = filmMapper;
    }
    


    public Film getFilmById(Long id){
        return filmRepository.findById(id)
               .orElseThrow(() -> new FilmNotFoundException(id));
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
    @Transactional
    public void ajouterActeur(Long filmId, Long acteurId){
        Film film = getFilmById(filmId);
        Acteur acteur = acteurRepository.findById(acteurId)
            .orElseThrow(() -> new ActeurNotFoundException(acteurId));
        film.addActeur(acteur);
    }

    @Transactional
    public void retirerActeur(Long filmId, Long acteurId){
        Film film = getFilmById(filmId);
        Acteur acteur = acteurRepository.findById(acteurId)
            .orElseThrow(() -> new ActeurNotFoundException(acteurId));
        film.getActeurs().remove(acteur);
    }
    @Transactional(readOnly = true)
public FilmDetailDto getFilmDetail(Long id){
    return filmMapper.toDetailDto(getFilmById(id));
}

}
