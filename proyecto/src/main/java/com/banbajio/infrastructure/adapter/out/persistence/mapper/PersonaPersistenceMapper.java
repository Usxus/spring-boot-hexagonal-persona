package com.banbajio.infrastructure.adapter.out.persistence.mapper;

import com.banbajio.domain.model.Persona;
import com.banbajio.infrastructure.adapter.out.persistence.PersonaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaPersistenceMapper {

    PersonaEntity toEntity(Persona domain);

    Persona toDomain(PersonaEntity entity);

    List<Persona> toDomainList(List<PersonaEntity> entityList);
}
