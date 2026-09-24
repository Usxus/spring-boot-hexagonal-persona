package com.banbajio.domain.port.in;

import com.banbajio.domain.model.Persona;
import java.util.List;

public interface PersonaInputPort {

    Persona registrarPersona(Persona persona);

    Persona obtenerPersonaPorId(Long id);

    List<Persona> obtenerTodasLasPersonas();

    Persona actualizarPersona(Long id, Persona persona);

    void eliminarPersona(Long id);
}
