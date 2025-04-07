package com.Altamira.domain;

import java.util.List;

public class Historial {

    private String idCompra;
    private String idUsuario;
    private List<Item> items;
    private double total;
    private String fechaCompra;

    // Constructor
    public Historial(String idCompra, String idUsuario, List<Item> items, String fechaCompra) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
        this.items = items;
        this.fechaCompra = fechaCompra;
        this.total = calcularTotal();  // Calcular el total en el momento de crear la compra
    }

    // Getters y Setters
    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        this.total = calcularTotal();  // Recálculo del total cuando se actualiza la lista de items
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    // Método para calcular el total de la compra
    private double calcularTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getCantidad();  // Sumar precio * cantidad por cada item
        }
        return total;
    }
}
