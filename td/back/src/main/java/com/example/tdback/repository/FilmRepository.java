package com.example.tdback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tdback.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{
    List<Film> findByActeursId(Long acteurId);

    @Query("select f from Film f join f.acteurs a where a.id = :acteurId")
    List<Film> findFilmsByActeur(@Param("acteurId") Long acteurId);

}


 