package com.Altamira.dao;

import com.Altamira.domain.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductoDao extends MongoRepository<Producto, String> {
    List<Producto> findByCategoria(String categoria);
}
