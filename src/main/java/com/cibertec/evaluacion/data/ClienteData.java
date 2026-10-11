package com.cibertec.evaluacion.data;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClienteData {

    public record Cliente(
            Long id,
            String nombre,
            String dni,
            Integer puntaje,
            Double ingresoMensual
    ) {}

    private final Map<Long, Cliente> clientes = Map.of(
            1L, new Cliente(1L, "Carlos Ramirez", "12345678", 780, 5000.0),
            2L, new Cliente(2L, "Maria Torres", "23456789", 690, 3500.0),
            3L, new Cliente(3L, "Luis Mendoza", "34567890", 580, 2500.0),
            4L, new Cliente(4L, "Ana Flores", "45678901", 420, 1800.0),
            5L, new Cliente(5L, "Pedro Castro", "56789012", 810, 8000.0)
    );

    public Cliente obtenerCliente(Long clienteId) {
        return clientes.get(clienteId);
    }
}