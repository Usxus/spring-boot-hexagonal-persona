package com.banbajio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
}
