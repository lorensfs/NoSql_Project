/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Altamira.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author indir
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Item extends Producto {

    private int cantidad; //Almacenar la cantidad de items de un producto
    private String id;
    public Item() {
    }

    public Item(Producto producto) {
        this.id = producto.getId();
        super.setNombre(producto.getNombre());
        super.setDescripcion(producto.getDescripcion());
        super.setRutaImagen(producto.getRutaImagen());
        super.setActivo(producto.isActivo());
        super.setCategoria(producto.getCategoria());
        super.setPrice(producto.getPrice());

        this.cantidad = 0;
    }
}
