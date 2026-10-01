package com.example.tdback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tdback.model.Acteur;

public interface ActeurRepository extends JpaRepository<Acteur, Long>{
    @Query("select a from Film f join f.acteurs a where f.id = :filmId")
    List<Acteur> findActeursByFilm(@Param("filmId") Long filmId);
    
}