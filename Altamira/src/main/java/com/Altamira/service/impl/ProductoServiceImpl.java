package com.Altamira.service.impl;

import com.Altamira.dao.ProductoDao;
import com.Altamira.domain.Producto;
import com.Altamira.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    private ProductoDao productoDao;

    @Override
    public Producto createProducto(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoDao.findAll();
    }

    @Override
    public Producto getProductoById(String id) {
        return productoDao.findById(id)
                .orElse(null);
    }

    @Override
    public Producto updateProducto(String id, Producto productoDetails) {
        return productoDao.findById(id).map(producto -> {
            producto.setDescripcion(productoDetails.getDescripcion());
            producto.setCategoria(productoDetails.getCategoria());
            producto.setRutaImagen(productoDetails.getRutaImagen());
            producto.setPrice(productoDetails.getPrice());
            producto.setActivo(productoDetails.isActivo());
            return productoDao.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado " + id));
    }

    @Override
    public void deleteProductoById(String id) {
        productoDao.deleteById(id);
    }

    @Override
    public List<Producto> getProductosByCategoria(String categoria) {
        return productoDao.findByCategoria(categoria);
    }
}