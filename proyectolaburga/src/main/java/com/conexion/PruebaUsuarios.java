
package com.conexion;

import com.dao.UsuarioDao;
import com.modelo.Usuario;
import java.util.List;


public class PruebaUsuarios {

  
    public static void main(String[] args) {
        
        System.out.println("inicio de prueba de control de usuarios");
        
        //metodo post registar un nuevo empleado 
        Usuario empleoAdministrador = new Usuario();
        
        //relleno el objeto con datos utilizando setter para enviar o guardar la infromacion
        
        empleoAdministrador.setNombreCompleto("Michael Benavides Hernandez");
        empleoAdministrador.setNombreUsuario("michaelben3");
        empleoAdministrador.setContraseñaUsuario("123456789");
        
        //le asigno el id del rol. le dejo 4 porque es el administrador
        empleoAdministrador.setIdRol(4);
        
        //instancio mi administradir de datos para usuario para poder enviarlos a mysql
        UsuarioDao administradorUsuarios = new UsuarioDao();
        
        System.out.println("intentando guardar usuario " + administradorUsuarios + "en el rol 4");
        
        //llamo al metodo registo de mi dao y guardo la respuesta  (tru o false) en mi variable testigo
        boolean resultadoRegistroUsuario = administradorUsuarios.registrarNuevoUsuario(empleoAdministrador);
        
        if(resultadoRegistroUsuario == true){
            System.out.println("paso 1 completado: se confirma creacion de usuario");
        }else{
            System.out.println("no se pudo copletar la tarea de creacion");
        }
        
        
        System.out.println("----------------------------------------------------------------");
        System.out.println("leer lista completa de usuarios");
        
        //llamo al metodo de lectura del dato y guardo los resultados en mi lista
        List<Usuario> listaDeUsuarios = administradorUsuarios.obtenerListaTodosLosUsuarios();
        //reviso si la carpte de lista de usuarios no regreso vacia de mysql
        if(listaDeUsuarios.isEmpty()== false){
            System.out.println("paso 2 completado: la operacion get funcioan perfecto");
            
            System.out.println("ttoal de usuarios encontrados en la tabla: " + listaDeUsuarios.size());
            
            //con un for recorro la lista fila por fila con un ciclo para ver la informacion
            for(Usuario usuarioFilaActual : listaDeUsuarios){
                System.out.println("* empleado: " + usuarioFilaActual.getNombreCompleto() + "| login: " + usuarioFilaActual.getNombreUsuario() + "| estado: " + usuarioFilaActual.getEstadoUsuario());
                
            }
        }else{
            System.out.println("aviso del get: la bse de datos respondio bien");
        }
        
        System.out.println("fin de la prueba");
      
    }
    
}
