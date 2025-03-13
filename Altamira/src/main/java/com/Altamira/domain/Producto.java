package com.Altamira.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "producto")
public class Producto implements Serializable {

    @Id
    private String id;

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    private String categoria;
    private Double price;

    private List<String> presentaciones;
}


