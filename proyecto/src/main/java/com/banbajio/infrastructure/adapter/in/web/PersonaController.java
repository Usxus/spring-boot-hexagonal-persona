package com.banbajio.infrastructure.adapter.in.web;

import com.banbajio.domain.model.Persona;
import com.banbajio.domain.port.in.PersonaInputPort;
import com.banbajio.infrastructure.adapter.in.web.dto.PersonaRequestDto;
import com.banbajio.infrastructure.adapter.in.web.dto.PersonaResponseDto;
import com.banbajio.infrastructure.adapter.in.web.mapper.PersonaRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Personas", description = "Endpoints para la gestión del ciclo de vida de personas (CRUD)")
@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaInputPort personaInputPort;
    private final PersonaRestMapper personaRestMapper;

    public PersonaController(PersonaInputPort personaInputPort, PersonaRestMapper personaRestMapper) {
        this.personaInputPort = personaInputPort;
        this.personaRestMapper = personaRestMapper;
    }

    @Operation(summary = "Registrar nueva persona", description = "Crea un nuevo registro de persona en el sistema con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Persona creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o campos requeridos faltantes"),
            @ApiResponse(responseCode = "409", description = "Conflicto de integridad de datos en la base de datos")
    })
    @PostMapping
    public ResponseEntity<PersonaResponseDto> registrarPersona(@Valid @RequestBody PersonaRequestDto dto) {
        Persona personaDomain = personaRestMapper.toDomain(dto);
        Persona personaCreada = personaInputPort.registrarPersona(personaDomain);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personaRestMapper.toResponseDto(personaCreada));
    }

    @Operation(summary = "Obtener persona por ID (PATH)", description = "Retorna la información de una persona específica buscando por su ID en la URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada con el ID especificado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDto> obtenerPorIdPath(
            @Parameter(description = "ID único de la persona a consultar", example = "1")
            @PathVariable Long id) {
        Persona persona = personaInputPort.obtenerPersonaPorId(id);
        return ResponseEntity.ok(personaRestMapper.toResponseDto(persona));
    }

    @Operation(summary = "Obtener persona por ID (QUERY)", description = "Retorna la información de una persona específica utilizando el parámetro de consulta 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada con el ID especificado")
    })
    @GetMapping("/obtener")
    public ResponseEntity<PersonaResponseDto> obtenerPorIdQuery(
            @Parameter(description = "ID único de la persona a consultar", example = "1")
            @RequestParam Long id) {
        Persona persona = personaInputPort.obtenerPersonaPorId(id);
        return ResponseEntity.ok(personaRestMapper.toResponseDto(persona));
    }

    @Operation(summary = "Obtener todas las personas", description = "Devuelve el listado completo de personas registradas en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<PersonaResponseDto>> obtenerTodas() {
        List<Persona> personas = personaInputPort.obtenerTodasLasPersonas();
        return ResponseEntity.ok(personaRestMapper.toResponseDtoList(personas));
    }

    @Operation(summary = "Actualizar persona", description = "Actualiza los datos de una persona existente identificada por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada para actualizar")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDto> actualizarPersona(
            @Parameter(description = "ID único de la persona a actualizar", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody PersonaRequestDto dto) {
        Persona personaDomain = personaRestMapper.toDomain(dto);
        Persona personaActualizada = personaInputPort.actualizarPersona(id, personaDomain);
        return ResponseEntity.ok(personaRestMapper.toResponseDto(personaActualizada));
    }

    @Operation(summary = "Eliminar persona por ID", description = "Elimina de forma permanente el registro de una persona del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Persona eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada para eliminar")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(
            @Parameter(description = "ID único de la persona a eliminar", example = "1")
            @PathVariable Long id) {
        personaInputPort.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
