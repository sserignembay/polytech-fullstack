package com.example.tdback.exception;

public class ActeurNotFoundException extends RuntimeException {

    public ActeurNotFoundException(Long id) {
        super("Acteur introuvable avec l'id " + id);
    }
}
