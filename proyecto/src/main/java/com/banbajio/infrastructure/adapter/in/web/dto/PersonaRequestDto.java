package com.banbajio.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO de solicitud para la creación o actualización de una persona")
public class PersonaRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre(s) de la persona", example = "Juan")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Schema(description = "Apellido(s) de la persona", example = "Pérez")
    private String apellido;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    @Schema(description = "Edad en años", example = "30", minimum = "0")
    private Integer edad;
}
