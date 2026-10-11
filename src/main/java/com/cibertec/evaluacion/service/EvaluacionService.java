package com.cibertec.evaluacion.service;

import com.cibertec.evaluacion.data.ClienteData;
import com.cibertec.evaluacion.dto.ClienteEvaluacionDTO;
import com.cibertec.evaluacion.dto.ScoringRiesgoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluacionService {

    private final ClienteData clienteData;

    @Value("${fintech.scoring-minimo-aprobacion:650}")
    private int scoringMinimo;

    @Value("${fintech.factor-endeudamiento-max:0.40}")
    private double factorEndeudamiento;

    public ScoringRiesgoDTO obtenerScoring(Long clienteId) {
        ClienteData.Cliente cliente = buscarCliente(clienteId);

        String nivelRiesgo = obtenerNivelRiesgo(cliente.puntaje());

        double capacidad = cliente.ingresoMensual() * factorEndeudamiento;

        boolean apto = cliente.puntaje() >= scoringMinimo
                && (nivelRiesgo.equals("BAJO") || nivelRiesgo.equals("MEDIO"));

        return new ScoringRiesgoDTO(
                cliente.id(),
                cliente.puntaje(),
                nivelRiesgo,
                capacidad,
                apto ? "APTO" : "NO_APTO"
        );
    }

    public ClienteEvaluacionDTO obtenerEvaluacionCliente(Long clienteId) {
        ClienteData.Cliente cliente = buscarCliente(clienteId);
        ScoringRiesgoDTO scoring = obtenerScoring(clienteId);

        return new ClienteEvaluacionDTO(
                cliente.id(),
                cliente.nombre(),
                cliente.dni(),
                scoring,
                scoring.getDictamenRiesgo()
        );
    }

    private ClienteData.Cliente buscarCliente(Long clienteId) {
        ClienteData.Cliente cliente = clienteData.obtenerCliente(clienteId);

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        return cliente;
    }

    private String obtenerNivelRiesgo(int puntaje) {
        if (puntaje >= 750) {
            return "BAJO";
        }
        if (puntaje >= 650) {
            return "MEDIO";
        }
        if (puntaje >= 500) {
            return "ALTO";
        }
        return "CRITICO";
    }
}