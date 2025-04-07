package com.Altamira.controller;

import com.Altamira.domain.Historial;
import com.Altamira.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    // Método para mostrar el historial de compras
    @GetMapping("/listado")
    public String mostrarHistorial(Model model) {
        // Obtener el historial de compras
        List<Historial> historialItems = historialService.obtenerHistorial();
        // Calcular el total de las compras
        double historialTotal = historialService.calcularTotal();

        // Agregar los datos al modelo
        model.addAttribute("historialItems", historialItems);
        model.addAttribute("historialTotal", historialTotal);

        // Retornar el nombre de la vista que se renderizará
        return "historial/listado";  // Aquí se hace referencia al archivo listado.html
    }

    // Método para agregar una compra al historial
    @PostMapping("/agregar")
    public void agregarCompra(@RequestBody Historial historial) {
        historialService.agregarCompra(historial);
    }

    // Método para eliminar una compra del historial
    @DeleteMapping("/eliminar")
    public void eliminarCompra(@RequestBody Historial historial) {
        historialService.eliminarCompra(historial);
    }

    // Método para obtener el total del historial de compras
    @GetMapping("/total")
    public double obtenerTotal() {
        return historialService.calcularTotal();
    }

    // Método para vaciar el historial de compras
    @DeleteMapping("/vaciar")
    public void vaciarHistorial() {
        historialService.vaciarHistorial();
    }
}
