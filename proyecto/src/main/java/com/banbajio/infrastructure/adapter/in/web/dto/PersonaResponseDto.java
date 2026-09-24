package com.banbajio.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO de respuesta con la información detallada de una persona")
public class PersonaResponseDto {

    @Schema(description = "Identificador único de la persona", example = "1")
    private Long id;

    @Schema(description = "Nombre(s) de la persona", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido(s) de la persona", example = "Pérez")
    private String apellido;

    @Schema(description = "Edad en años", example = "30")
    private Integer edad;
}
