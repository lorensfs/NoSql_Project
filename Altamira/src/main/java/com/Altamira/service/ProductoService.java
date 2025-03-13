package com.Altamira.service;

import com.Altamira.domain.Producto;
import java.util.List;


public interface ProductoService {

    // Crear un nuevo producto
    Producto createProducto(Producto producto);

    // Obtener la lista de productos
    List<Producto> getAllProductos();

    // Obtener un producto
    Producto getProductoById(String id);

    // Actualizar un producto
    Producto updateProducto(String id, Producto producto);

    // Eliminar un producto
    void deleteProductoById(String id);

    List<Producto> getProductosByCategoria(String categoria);
}
