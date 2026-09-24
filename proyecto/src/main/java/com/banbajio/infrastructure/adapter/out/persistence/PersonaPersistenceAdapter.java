package com.banbajio.infrastructure.adapter.out.persistence;

import com.banbajio.domain.model.Persona;
import com.banbajio.domain.port.out.PersonaOutputPort;
import com.banbajio.infrastructure.adapter.out.persistence.mapper.PersonaPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonaPersistenceAdapter implements PersonaOutputPort {

    private final SpringDataPersonaRepository springDataPersonaRepository;
    private final PersonaPersistenceMapper personaPersistenceMapper;

    public PersonaPersistenceAdapter(SpringDataPersonaRepository springDataPersonaRepository,
                                     PersonaPersistenceMapper personaPersistenceMapper) {
        this.springDataPersonaRepository = springDataPersonaRepository;
        this.personaPersistenceMapper = personaPersistenceMapper;
    }

    @Override
    public Persona guardar(Persona persona) {
        PersonaEntity entity = personaPersistenceMapper.toEntity(persona);
        PersonaEntity savedEntity = springDataPersonaRepository.save(entity);
        return personaPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Persona> buscarPorId(Long id) {
        return springDataPersonaRepository.findById(id)
                .map(personaPersistenceMapper::toDomain);
    }

    @Override
    public List<Persona> buscarTodas() {
        return personaPersistenceMapper.toDomainList(springDataPersonaRepository.findAll());
    }

    @Override
    public void eliminarPorId(Long id) {
        springDataPersonaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return springDataPersonaRepository.existsById(id);
    }
}
