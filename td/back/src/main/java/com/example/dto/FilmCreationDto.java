package com.example.tdback.dto;
import java.time.LocalDate;
import com.example.tdback.model.Genre;




public record FilmCreationDto(
   
    
     String titre,
     String realisateur,
     LocalDate dateSortie,                
     Genre genre)

{

}