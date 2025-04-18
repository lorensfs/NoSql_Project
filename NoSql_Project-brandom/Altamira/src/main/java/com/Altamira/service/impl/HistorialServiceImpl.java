package com.Altamira.service.impl;

import com.Altamira.dao.HistorialDao;
import com.Altamira.domain.Historial;

import com.Altamira.domain.Usuario;
import com.Altamira.service.HistorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialServiceImpl implements HistorialService {
    @Autowired
    HistorialDao historialDao;
    @Override
    @Transactional(readOnly = true)
    public List<Historial> findByIdUsuario(String id_usuario) {
        return historialDao.findByIdUsuario(id_usuario);
    }
    @Override
    @Transactional
    public void save(Historial historial) {

        historial = historialDao.save(historial);

    }

}
