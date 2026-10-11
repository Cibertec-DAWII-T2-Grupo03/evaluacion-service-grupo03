package com.cibertec.evaluacion.controller;

import com.cibertec.evaluacion.dto.ClienteEvaluacionDTO;
import com.cibertec.evaluacion.dto.ScoringRiesgoDTO;
import com.cibertec.evaluacion.service.EvaluacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    @GetMapping("/scoring/{clienteId}")
    public ScoringRiesgoDTO obtenerScoring(@PathVariable Long clienteId) {
        return evaluacionService.obtenerScoring(clienteId);
    }

    @GetMapping("/cliente/{clienteId}")
    public ClienteEvaluacionDTO obtenerEvaluacionCliente(
            @PathVariable Long clienteId) {
        return evaluacionService.obtenerEvaluacionCliente(clienteId);
    }
}