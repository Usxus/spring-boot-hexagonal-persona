package com.banbajio.domain.exception;

public class PersonaNotFoundException extends RuntimeException {

    public PersonaNotFoundException(Long id) {
        super("No se encontró la persona con el ID: " + id);
    }
}
