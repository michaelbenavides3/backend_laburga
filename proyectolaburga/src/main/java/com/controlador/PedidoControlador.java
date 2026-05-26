package com.controlador;

import com.dao.PedidoDao;
import com.modelo.Pedido;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PedidoControlador", urlPatterns = {"/PedidoControlador"})
public class PedidoControlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Atrapamos los datos básicos del formulario
        //atrapos el id de la mesa que viaja oculto en el (txtidmesa)
        int idMesa = Integer.parseInt(request.getParameter("txtIdMesa"));
        //atrapamos lo que viaja en caja de observaciones
        String observaciones = request.getParameter("txtObservaciones");

        //se abre la sesion actual del navegador
        HttpSession sesion = request.getSession();
        //sacamos el id del usuario real que se guardo al iniciar sesion
        Integer idUsuarioLogeaado = (Integer) sesion.getAttribute("idUsuario");

        int idMesero = (idUsuarioLogeaado != null) ? idUsuarioLogeaado : 3; //aqui va quedar el id del login o de rol por ahora es 1pe

        // El ID del mesero idealmente viene de la sesión activa, por ahora simulamos el 1 para no trabarnos
//        int idMesero = 1; 
        // 2. Registramos el pedido en la tabla pedidos
        Pedido nuevoPedido = new Pedido(0, idMesa, idMesero, "activo");
        //inicimaos el dao pra tener acceso a los metodos 
        PedidoDao pedidoDao = new PedidoDao();

//        boolean pedidoCreado = pedidoDao.registrarNuevoPedido(nuevoPedido);
        //ejecutamos el metodo y nos devuelve el id generado por mysql
        int idPedidoGenerado = pedidoDao.registrarNuevoPedido(nuevoPedido);

        if (idPedidoGenerado > 0) {

            //recorremos y gardamos los porductos seleccionado
            //ceamos un arreglo con todos los precios fijpos, de mi tabala productos de mysql
            //respetamos el orden del menu psocion1 hamburguesa clasica 19000 y asi hasta la utlima posicion
            double[] preciosProductos = {
                0.0, //posicion cero vacia
                19000.0, //id 1
                25000.0, //id 2
                22000.0, //id 3
                35000.0, //id 4
                19000.0, //id 5
                19000.0, //id 6
                19000.0, //id 7
                19000.0, //id 8
                25000.0, //id 9
                40000.0, //id 10
                5000.0, //id 11
                5000.0, //id 12
                7000.0, //id 13
                12000.0, //id 14
            };
            //obtenemos una lista con todos los nombres de los imput que envia el fomrularo
            Enumeration<String> nombresCampos = request.getParameterNames();

            //por medio del cilco while empezamos a revisar los cmapos 
            while (nombresCampos.hasMoreElements()) {
                String nombreCampo = nombresCampos.nextElement(); //captura el nombre del input ej(prod_1)
                //filtamos unicamnete los imputs que siren para la cantidades de comida (prod)
                if (nombreCampo.startsWith("prod_")) {
                    String valorCantidad = request.getParameter(nombreCampo);

                    //verificamsos que la caja de texto no vemga vacia o espacios en blanco
                    if (valorCantidad != null && !valorCantidad.trim().isEmpty()) {
                        int cantidad = Integer.parseInt(request.getParameter(nombreCampo));

                        // Si el mesero ingresó una cantidad mayor a 0, se procesa
                        if (cantidad > 0) {
                            // Extraemos el ID real del producto quitando el prefijo "prod_"
                            int idProducto = Integer.parseInt(nombreCampo.replace("prod_", ""));

                            //scamos el precio unitario correspodiente pasandole el id como posision del arreglo
                            double precioVenta = preciosProductos[idProducto];

                            pedidoDao.registrarDetallePedido(idPedidoGenerado, idProducto, cantidad, precioVenta);
                            System.out.println("Insertar en detallePedido: Producto ID: " + idProducto + " - Cantidad: " + cantidad);
                            // TODO: Aquí llamaremos al detallePedidoDao en el siguiente paso.
                        }
                    }
                }
            }

            // Si todo sale bien, devolvemos al mesero a su panel principal de mesas
            response.sendRedirect("html/m-meserocopy.jsp");
        } else {
            response.sendRedirect("html/m-registrar-pedido.jsp?error=3&idMesa=" + idMesa);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
