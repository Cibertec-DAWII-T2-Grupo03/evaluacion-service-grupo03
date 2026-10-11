package com.cibertec.evaluacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteEvaluacionDTO {

    private Long clienteId;
    private String nombreCliente;
    private String dni;
    private ScoringRiesgoDTO scoringActual;
    private String estadoCrediticioGlobal;
}