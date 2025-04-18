package com.Altamira.service;

import com.Altamira.domain.Historial;
import com.Altamira.domain.Usuario;

import java.util.List;

public interface HistorialService {

    List<Historial> findByIdUsuario(String id_usuario);

    public void save(Historial historial);

}
