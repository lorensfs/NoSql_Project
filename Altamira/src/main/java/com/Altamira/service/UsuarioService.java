package com.Altamira.service;

import java.util.List;
import com.Altamira.domain.Usuario;
import java.util.List;
public interface UsuarioService {

    public Usuario getUsuarioPorUsernameYPassword(String username, String password);


    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo);


    public void save(Usuario usuario,boolean crearRolUser);
    public boolean existsByPassword(String password);

}
