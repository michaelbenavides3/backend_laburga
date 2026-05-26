package com.dao;

import com.conexion.claseConexion; // Tu clase oficial de conectar a MySQL
import com.modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PedidoDao {

    // Método para crear la cabecera del pedido al darle "Registrar Pedido"
    public boolean registrarNuevoPedido(Pedido nuevoPedido) {
        Connection accesoBD = claseConexion.getConexion();
        PreparedStatement operacion;
        
        // SQL limpio apuntando a tus columnas reales
        String sqlQuery = "INSERT INTO pedidos (id_mesa, id_mesero, estado_pedido) VALUES (?, ?, ?)";
        
        try {
            operacion = accesoBD.prepareStatement(sqlQuery);
            
            // Reemplazamos las variables según la base de datos
            operacion.setInt(1, nuevoPedido.getIdMesa());
            operacion.setInt(2, nuevoPedido.getIdMesero());
            operacion.setString(3, nuevoPedido.getEstadoPedido()); // Por defecto 'activo'
            
            int filasInsertadas = operacion.executeUpdate();
            if (filasInsertadas > 0) {
                return true; // Éxito total
            }
        } catch (Exception error) {
            System.out.println("Error al guardar pedido en MySQL: " + error.getMessage());
        }
        
        return false;
    }
}