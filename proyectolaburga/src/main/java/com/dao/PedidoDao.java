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
        java.sql.PreparedStatement operacion = null;
        java.sql.PreparedStatement operacionMesa = null;
        java.sql.ResultSet resultadoClave = null;

        String sqlPedido = "INSERT INTO pedidos (id_mesa, id_mesero, estado_pedido) VALUES (?, ?, ?)";
        String sqlMesa = "UPDATE mesas SET estado_mesa = 'ocupada' WHERE id_mesas = ?";

        try {
            //insertamos el pedido
            operacion = accesoBD.prepareStatement(sqlPedido, java.sql.Statement.RETURN_GENERATED_KEYS);
            operacion.setInt(1, nuevoPedido.getIdMesa());
            operacion.setInt(2, nuevoPedido.getIdMesero());
            operacion.setString(3, nuevoPedido.getEstadoPedido()); 

            int filasInsertadas = operacion.executeUpdate();
            
            if (filasInsertadas > 0) {
                resultadoClave = operacion.getGeneratedKeys();
                if (resultadoClave.next()) {
                    int idPedidioGenerado = resultadoClave.getInt(1); //se guarad el id el pedido
                   // return resultadoClave.getInt(1); // Éxito total: devuelve el ID generado
                   
                   operacionMesa = accesoBD.prepareStatement(sqlMesa);
                   operacionMesa.setInt(1, nuevoPedido.getIdMesa());
                   operacionMesa.executeUpdate();
                   
                   return idPedidioGenerado;
                }
            }
        } catch (Exception error) {
            System.out.println("Error al guardar pedido en MySQL: " + error.getMessage());
        }finally{
            // Cerramos recursos adicionales de forma segura
            try { if (resultadoClave != null) resultadoClave.close(); } catch (Exception e) {}
            try { if (operacionMesa != null) operacionMesa.close(); } catch (Exception e) {}
            try { if (operacion != null) operacion.close(); } catch (Exception e) {}
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