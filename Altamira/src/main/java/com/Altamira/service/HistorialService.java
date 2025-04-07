package com.Altamira.service;

import com.Altamira.domain.Historial;

import java.util.List;

public interface HistorialService {

    List<Historial> obtenerHistorial();

    void agregarCompra(Historial historial);

    void eliminarCompra(Historial historial);

    double calcularTotal();

    void vaciarHistorial();  // Vaciar el historial de compras
}
