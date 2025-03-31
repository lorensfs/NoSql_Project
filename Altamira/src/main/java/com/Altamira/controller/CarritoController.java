/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Altamira.controller;

import com.Altamira.domain.Item;
import com.Altamira.domain.Producto;
import com.Altamira.service.ItemService;
import com.Altamira.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author indir
 */
@Controller
public class CarritoController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    //Para ver el carrito
    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrice());
        }
        model.addAttribute("carritoTotal",
                carritoTotalVenta);
        return "/carrito/listado";
    }

    //Para Agregar un producto al carrito
  @GetMapping("/carrito/agregar/{idProducto}")
public ModelAndView agregarItem(@PathVariable("idProducto") String idProducto, Model model, Item item) {
    if (idProducto == null || idProducto.trim().isEmpty()) {
        // Maneja el error o redirige a otra página si el ID es inválido
        return new ModelAndView("redirect:/error");
    }

    item.setId(idProducto);  // Asignar el idProducto al Item

    // Buscar el item en la lista de la sesión
    Item itemExistente = itemService.get(item);

    if (itemExistente == null) {
        Producto producto = productoService.getProducto(item);
        itemExistente = new Item(producto);  // Crear un nuevo item si no existe
    } else {
        itemExistente.setCantidad(itemExistente.getCantidad() + 1);  // Si existe, incrementar cantidad
    }

    itemService.save(itemExistente);  // Guardar el item en el carrito

    // Obtener la lista actualizada
    var lista = itemService.gets();

    // Calcular totales y agregar al modelo
    var totalCarritos = 0;
    var carritoTotalVenta = 0;
    for (Item i : lista) {
        totalCarritos += i.getCantidad();
        carritoTotalVenta += (i.getCantidad() * i.getPrice());
    }

    model.addAttribute("listaItems", lista);
    model.addAttribute("listaTotal", totalCarritos);
    model.addAttribute("carritoTotal", carritoTotalVenta);

    return new ModelAndView("/carrito/fragmentos :: verCarrito");
}



    //Para mofificar un producto del carrito
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificarItem(@PathVariable("idProducto") String idProducto ,Item item, Model model) {
        item.setId(idProducto);
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }

    //Para eliminar un elemento del carrito
 @GetMapping("/carrito/eliminar/{idProducto}")
public String eliminarItem(@PathVariable("idProducto") String idProducto) {
    Item item = new Item();
    item.setId(idProducto);
    itemService.delete(item);
    return "redirect:/carrito/listado";
}


    //Para actualizar un producto del carrito (cantidad)
    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }

    //Para facturar los productos del carrito... no implementado...
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }
}