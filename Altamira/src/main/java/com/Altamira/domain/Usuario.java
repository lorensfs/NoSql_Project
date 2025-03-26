package com.Altamira.domain;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;


@Data
@Document(collection = "usuario")
public class Usuario implements Serializable
{
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String rol;
    //falta hacer el carrito

}
