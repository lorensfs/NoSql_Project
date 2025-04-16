package com.Altamira.dao;

import com.Altamira.domain.Historial;
import com.Altamira.domain.Producto;
import com.Altamira.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistorialDao extends MongoRepository<Historial, String> {
    List<Historial> findByIdUsuario(String id_usuario);
}
