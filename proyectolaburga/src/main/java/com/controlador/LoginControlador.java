// Declaro la carpeta exacta donde estará guardado este archivo de control.
package com.controlador;

// Importo mi administrador de datos de usuarios y mi molde para procesar al empleado.
import com.dao.UsuarioDao;
import com.modelo.Usuario;

// Importo las librerías oficiales de Java Web para manejar peticiones de red de internet.
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Defino la etiqueta de ruta. Gracias a esto, cuando tu HTML busca "../LoginControlador", el servidor sabe que debe despertar a este archivo.
@WebServlet(name = "LoginControlador", urlPatterns = {"/LoginControlador"})
public class LoginControlador extends HttpServlet {

    // El método "processRequest" es la central de operaciones de mi Servlet. 
    // Captura los datos tanto si viajan por métodos web tradicionales de formularios.
    protected void processRequest(HttpServletRequest peticionWeb, HttpServletResponse respuestaWeb) throws ServletException, IOException {
        
        
        // PASO 1: ATRAPAR LAS CAJAS DE TEXTO DEL FRONTEND (HTML)
        
        // Uso "getParameter" buscando los nombres exactos que pusiste en los atributos 'name' de tus inputs HTML.
        String identificacionDigitada = peticionWeb.getParameter("txtUsuario");
        String claveDigitada = peticionWeb.getParameter("txtClave");

        // Instancio mi herramienta de base de datos para usuarios.
        UsuarioDao administradorUsuarios = new UsuarioDao();

        
        // PASO 2: MANDAR A VERIFICAR A LA BASE DE DATOS
        
        // Le paso los textos atrapados a mi método del DAO y el veredicto lo guardo en un objeto.
        Usuario empleadoLogeado = administradorUsuarios.verificarCredencialesIngreso(identificacionDigitada, claveDigitada);

        
        // PASO 3: TOMAR UNA DECISIÓN DE REDIRECCIÓN (CONTROLADOR)
        
        if (empleadoLogeado != null) {
            // SI EL EMPLEADO EXISTE: Abro una "Sesión web" para que el servidor recuerde su nombre en las páginas.
            HttpSession sesionActivaRestaurante = peticionWeb.getSession(true);
            sesionActivaRestaurante.setAttribute("usuarioLogeadoObjeto", empleadoLogeado);

            // Reviso qué rol tiene mi empleado para mandarlo a su pantalla visual correcta según tu diseño HTML:
            if (empleadoLogeado.getIdRol() == 4) {
                // Si es ID 4 (Administrador), lo redirijo a su panel de control.
                //si me confira que es rol1 lo tranfiero al sistema de pedidos
                respuestaWeb.sendRedirect("html/a-panel-principal-admin.html");
                System.out.println("inicio de seccion exitosa: perfil: administador");
            } else if (empleadoLogeado.getIdRol() == 1) {
                // Si es ID 1 (Mesero), lo mando a la pantalla de la carta o comandas.
                respuestaWeb.sendRedirect("html/m-registrar-pedido.html");
                System.out.println("inicio de seccion exitosa: perfil: mesero");
            } else if (empleadoLogeado.getIdRol() == 2) {
                // Si es ID 2 (Cajero), lo redirijo al módulo de caja.
                respuestaWeb.sendRedirect("html/cajero.html");
                System.out.println("inicio de seccion exitosa: perfil: cajero");
            } else {
                // Si es otro cargo (como cocinero), lo devuelvo al inicio de bienvenida por seguridad.
                respuestaWeb.sendRedirect("index.html");
            }

        } else {
            // SI LAS CREDENCIALES ESTÁN MAL: Lo regreso de forma obligatoria a tu pantalla de t-login.html
            // Le anexo un parámetro en la URL para avisarle a tu diseño que hubo un error de clave.
            respuestaWeb.sendRedirect("html/t-login.html?error=1");
            System.out.println("intento de acceso denegado, para las credenciales: " + identificacionDigitada);
        }
    }

    // Vinculo el método GET de internet para que redirija el flujo automáticamente a mi central de procesamiento.
    @Override
    protected void doGet(HttpServletRequest peticionWeb, HttpServletResponse respuestaWeb)
            throws ServletException, IOException {
        processRequest(peticionWeb, respuestaWeb);
    }

    // Vinculo el método POST de tu formulario HTML para que mande los datos de forma segura a mi central.
    @Override
    protected void doPost(HttpServletRequest peticionWeb, HttpServletResponse respuestaWeb)
            throws ServletException, IOException {
        processRequest(peticionWeb, respuestaWeb);
    }
}
