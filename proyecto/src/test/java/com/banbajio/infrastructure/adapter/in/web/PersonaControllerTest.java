package com.banbajio.infrastructure.adapter.in.web;

import com.banbajio.domain.model.Persona;
import com.banbajio.domain.port.in.PersonaInputPort;
import com.banbajio.infrastructure.adapter.in.web.dto.PersonaRequestDto;
import com.banbajio.infrastructure.adapter.in.web.dto.PersonaResponseDto;
import com.banbajio.infrastructure.adapter.in.web.mapper.PersonaRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PersonaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonaInputPort personaInputPort;

    @Mock
    private PersonaRestMapper personaRestMapper;

    @InjectMocks
    private PersonaController personaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personaController).build();
    }

    @Test
    void registrarPersona_Exito() throws Exception {
        Persona domainPersona = Persona.builder().id(1L).nombre("Juan").apellido("Pérez").edad(30).build();
        PersonaResponseDto responseDto = PersonaResponseDto.builder().id(1L).nombre("Juan").apellido("Pérez").edad(30).build();

        when(personaRestMapper.toDomain(any(PersonaRequestDto.class))).thenReturn(domainPersona);
        when(personaInputPort.registrarPersona(any(Persona.class))).thenReturn(domainPersona);
        when(personaRestMapper.toResponseDto(any(Persona.class))).thenReturn(responseDto);

        String jsonPayload = "{\"nombre\":\"Juan\",\"apellido\":\"Pérez\",\"edad\":30}";

        mockMvc.perform(post("/api/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated());
    }

    @Test
    void obtenerPorIdPath_Exito() throws Exception {
        Persona domainPersona = Persona.builder().id(1L).nombre("Juan").apellido("Pérez").edad(30).build();
        PersonaResponseDto responseDto = PersonaResponseDto.builder().id(1L).nombre("Juan").apellido("Pérez").edad(30).build();

        when(personaInputPort.obtenerPersonaPorId(1L)).thenReturn(domainPersona);
        when(personaRestMapper.toResponseDto(domainPersona)).thenReturn(responseDto);

        mockMvc.perform(get("/api/personas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerTodas_Exito() throws Exception {
        Persona domainPersona = Persona.builder().id(1L).nombre("Juan").apellido("Pérez").edad(30).build();
        PersonaResponseDto responseDto = PersonaResponseDto.builder().id(1L).nombre("Juan").apellido("Pérez").edad(30).build();

        when(personaInputPort.obtenerTodasLasPersonas()).thenReturn(List.of(domainPersona));
        when(personaRestMapper.toResponseDtoList(any())).thenReturn(List.of(responseDto));

        mockMvc.perform(get("/api/personas"))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarPersona_Exito() throws Exception {
        Persona domainPersona = Persona.builder().id(1L).nombre("Juan Carlos").apellido("Pérez").edad(31).build();
        PersonaResponseDto responseDto = PersonaResponseDto.builder().id(1L).nombre("Juan Carlos").apellido("Pérez").edad(31).build();

        when(personaRestMapper.toDomain(any(PersonaRequestDto.class))).thenReturn(domainPersona);
        when(personaInputPort.actualizarPersona(eq(1L), any(Persona.class))).thenReturn(domainPersona);
        when(personaRestMapper.toResponseDto(any(Persona.class))).thenReturn(responseDto);

        String jsonPayload = "{\"nombre\":\"Juan Carlos\",\"apellido\":\"Pérez\",\"edad\":31}";

        mockMvc.perform(put("/api/personas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarPersona_Exito() throws Exception {
        doNothing().when(personaInputPort).eliminarPersona(1L);

        mockMvc.perform(delete("/api/personas/1"))
                .andExpect(status().isNoContent());
    }
}
