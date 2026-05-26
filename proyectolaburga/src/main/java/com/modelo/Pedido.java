package com.modelo;

import java.sql.Timestamp;

public class Pedido {
    // Atributos exactos de la tabla en MySQL
    private int idPedido;
    private int idMesa;
    private int idMesero;
    private Timestamp fechaPedido; // Para registrar el tiempo real
    private String estadoPedido; // ENUM: 'activo', 'preparacion', etc. por default lo toma activo

    // Constructor vacío
    public Pedido() {
    }

    // Constructor lleno para registrar nuevos pedidos
    public Pedido(int idPedido, int idMesa, int idMesero, String estadoPedido) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.idMesero = idMesero;
        this.estadoPedido = estadoPedido;
    }

    // Getters y Setters sencillos
    public int getIdPedido() { 
        return idPedido; 
    }
    public void setIdPedido(int idPedido) {
    { this.idPedido = idPedido; }
    }
    public int getIdMesa() { 
        return idMesa; 
    }
    public void setIdMesa(int idMesa) { 
        this.idMesa = idMesa; 
    }

    public int getIdMesero() { 
        return idMesero; 
    }
    public void setIdMesero(int idMesero) { 
        this.idMesero = idMesero; 
    }

    public Timestamp getFechaPedido() { 
        return fechaPedido; 
    }
    public void setFechaPedido(Timestamp fechaPedido) { 
        this.fechaPedido = fechaPedido; 
    }

    public String getEstadoPedido() { 
        return estadoPedido; 
    }
    public void setEstadoPedido(String estadoPedido) { 
        this.estadoPedido = estadoPedido; 
    }
}