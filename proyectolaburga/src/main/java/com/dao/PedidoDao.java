package com.dao;

import com.conexion.claseConexion; // Tu clase oficial de conectar a MySQL
import com.modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PedidoDao {

   
    // MÉTODO 1: Para crear el pedido 
   
    public int registrarNuevoPedido(Pedido nuevoPedido) {
        java.sql.Connection accesoBD = com.conexion.claseConexion.getConexion();
        java.sql.PreparedStatement operacion;
        java.sql.ResultSet resultadoClave;

        String sqlQuery = "INSERT INTO pedidos (id_mesa, id_mesero, estado_pedido) VALUES (?, ?, ?)";

        try {
            operacion = accesoBD.prepareStatement(sqlQuery, java.sql.Statement.RETURN_GENERATED_KEYS);

            operacion.setInt(1, nuevoPedido.getIdMesa());
            operacion.setInt(2, nuevoPedido.getIdMesero());
            operacion.setString(3, nuevoPedido.getEstadoPedido()); 

            int filasInsertadas = operacion.executeUpdate();
            
            if (filasInsertadas > 0) {
                resultadoClave = operacion.getGeneratedKeys();
                if (resultadoClave.next()) {
                    return resultadoClave.getInt(1); // Éxito total: devuelve el ID generado
                }
            }
        } catch (Exception error) {
            System.out.println("Error al guardar pedido en MySQL: " + error.getMessage());
        }

        return 0; // Si falla devuelve 0
    }

   
    // MÉTODO 2:para guardar los productos del pedido
   
    public boolean registrarDetallePedido(int idPedido, int idProducto, int cantidad, double precioVenta) {
        Connection accesoBD = claseConexion.getConexion();
        PreparedStatement operacion;
        
        // SQL limpio apuntando a tu tabla de detalles (Ajusta los nombres si cambian en tu BD)
        String sqlQuery = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        
        try {
            operacion = accesoBD.prepareStatement(sqlQuery);
            operacion.setInt(1, idPedido);
            operacion.setInt(2, idProducto);
            operacion.setInt(3, cantidad);
            operacion.setDouble(4, precioVenta);
            
            int filasInsertadas = operacion.executeUpdate();
            return filasInsertadas > 0; // Devuelve true si se guardó el producto con éxito
        } catch (Exception error) {
            System.out.println("Error en el detalle del pedido DAO: " + error.getMessage());
            return false;
        }
    }
}