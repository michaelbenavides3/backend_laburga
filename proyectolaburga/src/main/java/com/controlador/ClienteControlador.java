package com.controlador;

// Importamos el DAO y el Modelo de Labur-GA que ya probamos con éxito
import com.dao.ClienteDao;
import com.modelo.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Este Servlet actúa como el intermediario oficial (Controlador) entre la vista
 * del formulario web y la persistencia de datos en MySQL (DAO). Mapeado con la
 * ruta virtual /registrarCliente.
 */
@WebServlet("/registrarCliente")
public class ClienteControlador extends HttpServlet {

    // Capta las peticiones de envío seguro de datos (Método POST del formulario)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //  CODIFICACIÓN DE SEGURIDAD: Forzamos la lectura en UTF-8 para que las tildes y eñes viajen sin romperse
        request.setCharacterEncoding("UTF-8");

        //  RECOLECCIÓN DE DATOS: Atrapamos los textos que el mesero digitó en las cajas del formulario web
        String nombreFormulario = request.getParameter("nombre");
        String documentoFormulario = request.getParameter("documento");
        String telefonoFormulario = request.getParameter("telefono");
        String correoFormulario = request.getParameter("correo");
        //  INVOCACIÓN DE LA CAPA MODELO: Instanciamos el DAO para ejecutar el proceso transaccional en las 3 tablas
        ClienteDao dao = new ClienteDao();

        //valida que el numero tenga 10 digitos
        if(telefonoFormulario == null || telefonoFormulario.length() !=10 || !telefonoFormulario.matches("\\d+")){
            System.out.println("error el telefono no tiene el formato correcto");
            //si falla regresaereos al formulario 
            response.sendRedirect("html/m-formulario-clientenuevo-mesero.jsp");
            return;
        }
        
        
        //llamadmos al nuevo metdo pasando la cc del formulario
        //si regresa true siginifica que el where encontro la cc en mysql
        if (dao.existeCliente(documentoFormulario)) {
            System.out.println("registrio denegado la cc" + documentoFormulario + "ya existe");

            response.sendRedirect("html/m-meserocopy.jsp?estado=duplicado");
            return; //se rompe el flujo 
        }

       

        //  EMPAQUETADO DEL OBJETO: Creamos una instancia del modelo Cliente y le cargamos los datos recolectados
        Cliente nuevoCliente = new Cliente(nombreFormulario, documentoFormulario, telefonoFormulario, correoFormulario);
        boolean operacionActualizacionExitosa = dao.registrarClienteCompleto(nuevoCliente);

        //  ENRUTAMIENTO INTELIGENTE: Evaluamos el resultado de MySQL para decidir la respuesta visual
        if (operacionActualizacionExitosa) {
            // Si el cliente se guardó en cascada perfectamente, imprimimos confirmación en consola...
            System.out.println(" Cliente registrado con éxito en la base de datos.");
            // ... y redirigimos al mesero de golpe al panel de control de las mesas
            response.sendRedirect("html/m-meserocopy.jsp?estado=exitoso");
        } else {
            // Si ocurrió un fallo (ejemplo: documento duplicado), enviamos reporte al log...
            System.out.println(" Error crítico, no se pudo procesar el registro.");
            // ... y devolvemos al mesero al formulario pintando una señal de error
            response.sendRedirect("html/m-meserocopy.jsp?estado=error");
        }
        
       
         
    }
}
