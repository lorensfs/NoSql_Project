package com.Altamira.service.impl;

import com.Altamira.domain.Historial;
import com.Altamira.domain.Item;
import com.Altamira.service.HistorialService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialServiceImpl implements HistorialService {

    @Autowired
    private HttpSession session;

    @Override
    public List<Historial> obtenerHistorial() {
        List<Historial> historial = (List) session.getAttribute("historialCompras");
        return historial != null ? historial : new ArrayList<>();
    }

    @Override
    public void agregarCompra(Historial historial) {
        List<Historial> historialCompras = (List) session.getAttribute("historialCompras");
        if (historialCompras == null) {
            historialCompras = new ArrayList<>();
        }
        historialCompras.add(historial);
        session.setAttribute("historialCompras", historialCompras);  // Guardar el historial de compras en la sesión
    }

    @Override
    public void eliminarCompra(Historial historial) {
        List<Historial> historialCompras = (List) session.getAttribute("historialCompras");
        if (historialCompras != null) {
            historialCompras.remove(historial);
            session.setAttribute("historialCompras", historialCompras);
        }
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        List<Historial> historialCompras = (List) session.getAttribute("historialCompras");
        if (historialCompras != null) {
            for (Historial historial : historialCompras) {
                total += historial.getTotal();
            }
        }
        return total;
    }

    // Método para vaciar el historial de compras
    @Override
    public void vaciarHistorial() {
        session.setAttribute("historialCompras", new ArrayList<>());
    }
}
