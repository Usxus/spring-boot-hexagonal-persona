package com.banbajio.application.service;

import com.banbajio.domain.exception.PersonaNotFoundException;
import com.banbajio.domain.model.Persona;
import com.banbajio.domain.port.out.PersonaOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private PersonaOutputPort personaOutputPort;

    @InjectMocks
    private PersonaService personaService;

    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = Persona.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Pérez")
                .edad(30)
                .build();
    }

    @Test
    void registrarPersona_Exito() {
        when(personaOutputPort.guardar(any(Persona.class))).thenReturn(persona);

        Persona resultado = personaService.registrarPersona(persona);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(personaOutputPort, times(1)).guardar(persona);
    }

    @Test
    void obtenerPersonaPorId_Exito() {
        when(personaOutputPort.buscarPorId(1L)).thenReturn(Optional.of(persona));

        Persona resultado = personaService.obtenerPersonaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(personaOutputPort, times(1)).buscarPorId(1L);
    }

    @Test
    void obtenerPersonaPorId_NoEncontrada_LanzaExcepcion() {
        when(personaOutputPort.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(PersonaNotFoundException.class, () -> personaService.obtenerPersonaPorId(1L));
        verify(personaOutputPort, times(1)).buscarPorId(1L);
    }

    @Test
    void obtenerTodasLasPersonas_Exito() {
        when(personaOutputPort.buscarTodas()).thenReturn(List.of(persona));

        List<Persona> resultado = personaService.obtenerTodasLasPersonas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(personaOutputPort, times(1)).buscarTodas();
    }

    @Test
    void actualizarPersona_Exito() {
        Persona personaActualizada = Persona.builder()
                .id(1L)
                .nombre("Juan Carlos")
                .apellido("Pérez")
                .edad(31)
                .build();

        when(personaOutputPort.buscarPorId(1L)).thenReturn(Optional.of(persona));
        when(personaOutputPort.guardar(any(Persona.class))).thenReturn(personaActualizada);

        Persona resultado = personaService.actualizarPersona(1L, personaActualizada);

        assertNotNull(resultado);
        assertEquals("Juan Carlos", resultado.getNombre());
        verify(personaOutputPort, times(1)).buscarPorId(1L);
        verify(personaOutputPort, times(1)).guardar(any(Persona.class));
    }

    @Test
    void eliminarPersona_Exito() {
        when(personaOutputPort.existePorId(1L)).thenReturn(true);
        doNothing().when(personaOutputPort).eliminarPorId(1L);

        personaService.eliminarPersona(1L);

        verify(personaOutputPort, times(1)).existePorId(1L);
        verify(personaOutputPort, times(1)).eliminarPorId(1L);
    }

    @Test
    void eliminarPersona_NoExiste_LanzaExcepcion() {
        when(personaOutputPort.existePorId(1L)).thenReturn(false);

        assertThrows(PersonaNotFoundException.class, () -> personaService.eliminarPersona(1L));
        verify(personaOutputPort, times(1)).existePorId(1L);
        verify(personaOutputPort, times(0)).eliminarPorId(1L);
    }
}
