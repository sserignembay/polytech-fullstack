package com.example.tdback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.tdback.model.Film;

@Repository
public class FilmRepository{
    private final Map<Long, Film> films =new ConcurrentHashMap<>();
    private final AtomicLong compteur = new AtomicLong(0);

public List<Film> findAll(){
    return new ArrayList<>(films.values());
}
public Film findById(Long id){
    return films.get(id);
}

public Film save(Film film){
    if(film.getId() == null){
        film.setId(compteur.incrementAndGet());
    }
    films.put(film.getId(), film);
    return film;
}
public void deleteById(Long id){
    films.remove(id);
}
public boolean existsById(Long id){
    return films.containsKey(id);
}
}   