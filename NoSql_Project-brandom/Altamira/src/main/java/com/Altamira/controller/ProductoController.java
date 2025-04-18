package com.Altamira.controller;

import com.Altamira.domain.Producto;
import com.Altamira.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Slf4j
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping
    public String productos() {
        return "productos";
    }

    @GetMapping("/cloros/lista")
    public String getAllCloros(Model model) {
        List<Producto> cloros = productoService.getProductosByCategoria("cloro").
                stream().filter(producto -> producto.isActivo()).toList();
        model.addAttribute("productos", cloros);
        return "cloros/lista";
    }

    @GetMapping("/cloros/{id}")
    public String getCloroById(@PathVariable String id, Model model) {
        Producto producto = productoService.getProductoById(id);

        if (producto == null || !producto.isActivo()) {
            return "redirect:cloros/lista";
        }

        model.addAttribute("producto", producto);

        List<Producto> relatedProducts = productoService.getProductosByCategoria(producto.getCategoria())
                .stream()
                .filter(p -> p.isActivo() && !p.getId().equals(id))
                .toList();

        model.addAttribute("relatedProducts", relatedProducts);

        return "buyproducts";
    }

    @GetMapping("/manage")
    public String manageProductos(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productoService.getAllProductos());
        return "manageProductos";
    }

    @PostMapping("/add")
    public String addProducto(@ModelAttribute Producto producto) {
        producto.setActivo(true);
        productoService.createProducto(producto);
        return "redirect:/productos/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteProducto(@PathVariable String id) {
        productoService.deleteProductoById(id);
        return "redirect:/productos/manage";
    }

}