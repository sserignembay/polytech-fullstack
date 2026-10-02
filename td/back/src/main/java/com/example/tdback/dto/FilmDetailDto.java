package com.example.tdback.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.tdback.model.Genre;

public record FilmDetailDto(
    Long id,
    String titre,
    String realisateur,
    LocalDate dateSortie,
    Genre genre,
    List<ActeurDto> acteurs
) {
}