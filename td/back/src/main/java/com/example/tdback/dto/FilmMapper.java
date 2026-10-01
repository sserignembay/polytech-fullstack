package com.example.tdback.dto;

import com.example.tdback.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper{
    public FilmDto toDto(Film film){
        return new FilmDto(
            film.getId(),
            film.getTitre(),
            film.getRealisateur(),
            film.getDateSortie(),
            film.getGenre()

        );
    }

    public Film toEntity(FilmCreationDto dto){
        Film film = new Film();
        film.setTitre(dto.titre());
        film.setRealisateur(dto.realisateur());
        film.setDateSortie(dto.dateSortie());
        film.setGenre(dto.genre());
        return film;
    }
}