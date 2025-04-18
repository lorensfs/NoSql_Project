package com.Altamira.domain;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@Document(collection = "historial")
public class Historial implements Serializable {
    @Id
    private String id;
    private String idUsuario;
    private String id_producto;
    private LocalDate fechaCompra;
    private String rutaImagen;
    private String nombre;
    private int cantidad;
    private double precio;
}

