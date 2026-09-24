package com.banbajio.infrastructure.adapter.in.web.mapper;

import com.banbajio.domain.model.Persona;
import com.banbajio.infrastructure.adapter.in.web.dto.PersonaRequestDto;
import com.banbajio.infrastructure.adapter.in.web.dto.PersonaResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaRestMapper {

    @Mapping(target = "id", ignore = true)
    Persona toDomain(PersonaRequestDto dto);

    PersonaResponseDto toResponseDto(Persona domain);

    List<PersonaResponseDto> toResponseDtoList(List<Persona> domainList);
}
