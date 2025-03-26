package com.Altamira.dao;

import com.Altamira.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface UsuarioDao extends MongoRepository<Usuario, String>{


    Usuario findByUsernameAndPassword(String username, String password);



    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByPassword(String password);
}
