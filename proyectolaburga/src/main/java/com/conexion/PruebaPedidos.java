
package com.conexion;

import com.dao.PedidoDao;
import com.modelo.Pedido;

public class PruebaPedidos {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DE REGISTRO DE PEDIDO ===");

        // 1. Instanciamos el DAO del pedido
        PedidoDao pedidoDao = new PedidoDao();

        // 2. Creamos un objeto Pedido ficticio para simular la Mesa 1
        // Pasamos: (idPedido, idMesa, idMesero, estadoPedido)
        // Usamos el idPedido en 0 porque es AUTO_INCREMENT en tu base de datos.
        // Asegúrate de que el idMesero (ej: 1 o el ID de un usuario real) exista en tu tabla usuario.
        Pedido pedidoPrueba = new Pedido(0, 1, 1, "activo");

        System.out.println("Enviando datos a MySQL: Mesa " + pedidoPrueba.getIdMesa() + " - Mesero " + pedidoPrueba.getIdMesero());

        // 3. Ejecutamos el método del DAO
        boolean resultado = pedidoDao.registrarNuevoPedido(pedidoPrueba);

        // 4. Verificamos la respuesta del servidor
        if (resultado) {
            System.out.println("¡ÉXITO TOTAL! El pedido se registró melo en la base de datos.");
        } else {
            System.out.println("¡FALTA EN EL SISTEMA! Revisa la consola para ver el error de SQL.");
        }
    }
}
