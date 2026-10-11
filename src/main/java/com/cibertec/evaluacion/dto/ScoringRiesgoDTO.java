package com.cibertec.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoringRiesgoDTO {

    private Long clienteId;
    private Integer puntajeCrediticio;
    private String nivelRiesgo;
    private Double capacidadMaxEndeudamiento;
    private String dictamenRiesgo;
}