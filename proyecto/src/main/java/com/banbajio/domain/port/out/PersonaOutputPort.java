package com.banbajio.domain.port.out;

import com.banbajio.domain.model.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaOutputPort {

    Persona guardar(Persona persona);

    Optional<Persona> buscarPorId(Long id);

    List<Persona> buscarTodas();

    void eliminarPorId(Long id);

    boolean existePorId(Long id);
}
