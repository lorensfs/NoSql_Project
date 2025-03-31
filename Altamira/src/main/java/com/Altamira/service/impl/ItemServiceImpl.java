/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Altamira.service.impl;

import com.Altamira.domain.Item;
import com.Altamira.service.ItemService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author indir
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpSession session;

    @Override
    public List<Item> gets() {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        return listaItems;
    }

    @Override
    public Item get(Item item) {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (Item i : listaItems) {
                if (i.getIdProducto() == item.getIdProducto()) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Item item) {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            var posicion = -1;
            var existe = false;
            for (Item i : listaItems) {
                posicion++;
                if (i.getIdProducto() == item.getIdProducto()) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                listaItems.remove(posicion);
                session.setAttribute("listaItems", listaItems);
            }
        }
    }

@Override
public void save(Item item) {
    List<Item> listaItems = (List) session.getAttribute("listaItems");
    if (listaItems == null) {
        listaItems = new ArrayList<>();
    }
    var existe = false;
    for (Item i : listaItems) {
        // Verificar si idProducto no es null antes de la comparación
        if (i.getIdProducto() != null && i.getIdProducto().equals(item.getIdProducto())) {
            existe = true;
            i.setCantidad(i.getCantidad() + 1);  // Incrementar la cantidad
            break;
        }
    }
    if (!existe) {
        item.setCantidad(1);  // Si el producto no existe, agregarlo con cantidad 1
        listaItems.add(item);
    }
    session.setAttribute("listaItems", listaItems);  // Guardar los items en la sesión
}



    @Override
    public void update(Item item) {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (Item i : listaItems) {
                if (i.getIdProducto() == item.getIdProducto()) {
                    i.setCantidad(item.getCantidad());
                    session.setAttribute("listaItems", listaItems);
                    break;
                }
            }
        }
    }

    @Override
    public void facturar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getTotal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
