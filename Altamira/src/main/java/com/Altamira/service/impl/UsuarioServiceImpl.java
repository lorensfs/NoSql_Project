package com.Altamira.service.impl;

import com.Altamira.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.Altamira.dao.UsuarioDao;
import com.Altamira.domain.Usuario;
import org.springframework.stereotype.Service;
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDao usuarioDao;


    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String email) {
        return usuarioDao.existsByUsernameOrEmail(username, email);
    }
    @Override
    @Transactional(readOnly = true)
    public boolean existsByPassword(String password) {
        return usuarioDao.existsByPassword(password);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        usuario.setRol("USER");
        usuario = usuarioDao.save(usuario);

    }

}
