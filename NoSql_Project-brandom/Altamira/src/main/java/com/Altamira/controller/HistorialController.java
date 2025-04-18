package com.Altamira.controller;

import com.Altamira.domain.Historial;
import com.Altamira.domain.HistorialForm;
import com.Altamira.domain.Item;
import com.Altamira.domain.Usuario;
import com.Altamira.service.HistorialService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;
    @PostMapping("/guardarHistorial")
    public String guardarHistorial(@ModelAttribute HistorialForm historialForm, @SessionAttribute("id_usuario")
    String idUsuario,HttpSession session) {
        for (Historial h : historialForm.getHistoriales()) {
            h.setIdUsuario(idUsuario);
            LocalDate fechaActual = LocalDate.now();
            h.setFechaCompra(fechaActual);
            historialService.save(h);

        }
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        listaItems.clear();
        return "redirect:/usuarios/usuario";
    }

    @GetMapping("/verHistorial")
    public String historial(Model model, @SessionAttribute("id_usuario")String idUsuario) {
        List<Historial> historial = historialService.findByIdUsuario(idUsuario);
        System.out.println("Historial: " + historial);
        model.addAttribute("historial", historial);
        return "historial/verHistorial";
    }


}
