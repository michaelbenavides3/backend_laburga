
package com.conexion;

// se importan las herramnientas de java para manejar conexion a base de datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class claseConexion {
    // se define la direccion de mi servidor en mysql
    private static final String URL = "jdbc:mysql://localhost:3306/restauranteLaburGA";
    //se define el usuario de administrador
    private static final String USER = "root";
    //se define la contraseña de la base de datos
    //private static final String PASSWORD = "#Aprendiz2024";
    private static final String PASSWORD = "Lauraluna94.";
    
    //creamos el metodo principal para obtener la conexion, se deja
    //static para porder llamar despues del dao y no tener que crear un objeto de esta clase cada vez
    
    public static Connection getConexion() {
        
        //creo una variable vacia para guardar la conexion mas adelante
        //empieza la conexion null (vacia) para limpiarla antes de conectame a la base de datos
        Connection conexion = null;
        
        try{
            //se activa el traducto de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            //si todo coincide la comunicacion se guarda dentro de mi variable conexion
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            //si funciona y se abre con exito se imprime este mensaje acontinuacion
            System.out.println("¡ conexion exitosa a la base de datos ! :D ");
        }catch(ClassNotFoundException errorEstructura){
            //si el programa no encutra la bd de mi proyecto se imprime este mensaje
            System.out.println("error: no se encontro el driver de mysql en el puerto " + errorEstructura.getMessage());
        }catch(SQLException errorBaseDatos){
            //si escribe mal el nombre de la base de datos arrogar el sieguieente error
            System.out.println("error: no se puede conectar a la base de datos porque los datos estan mal " + errorBaseDatos.getMessage());
        }
        //al final regreso la variable conexion si todo sale bien tendra la conexion lista si falla devolvera null
        return conexion;
    }   
    
}
