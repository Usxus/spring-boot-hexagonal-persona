package com.banbajio.application.service;

import com.banbajio.domain.exception.PersonaNotFoundException;
import com.banbajio.domain.model.Persona;
import com.banbajio.domain.port.in.PersonaInputPort;
import com.banbajio.domain.port.out.PersonaOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaService implements PersonaInputPort {

    private final PersonaOutputPort personaOutputPort;

    public PersonaService(PersonaOutputPort personaOutputPort) {
        this.personaOutputPort = personaOutputPort;
    }

    @Override
    @Transactional
    public Persona registrarPersona(Persona persona) {
        return personaOutputPort.guardar(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona obtenerPersonaPorId(Long id) {
        return personaOutputPort.buscarPorId(id)
                .orElseThrow(() -> new PersonaNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> obtenerTodasLasPersonas() {
        return personaOutputPort.buscarTodas();
    }

    @Override
    @Transactional
    public Persona actualizarPersona(Long id, Persona personaActualizada) {
        Persona personaExistente = obtenerPersonaPorId(id);

        personaExistente.setNombre(personaActualizada.getNombre());
        personaExistente.setApellido(personaActualizada.getApellido());
        personaExistente.setEdad(personaActualizada.getEdad());

        return personaOutputPort.guardar(personaExistente);
    }

    @Override
    @Transactional
    public void eliminarPersona(Long id) {
        if (!personaOutputPort.existePorId(id)) {
            throw new PersonaNotFoundException(id);
        }
        personaOutputPort.eliminarPorId(id);
    }
}
