
package com.conexion;
import com.dao.PermisosDAO;
import com.modelo.Permisos;


public class PruebaIngresarPermisos {

    
    public static void main(String[] args) {
       
        System.out.println("iniciando registros");
        
        //instancio mi administrador de datos para poder enviarlos a mysql
        PermisosDAO administradorPermisos = new PermisosDAO();
        
        //creo una variable reutilizable
        
        Permisos permisoTemporal;
        
        //seccion control de pedidos para mesero
        
        permisoTemporal = new Permisos();
        permisoTemporal.setSlugPermisos("pedido.crear");
        permisoTemporal.setNombrePermiso("crear pedidos");
        permisoTemporal.setDescripcionPermiso("permite registrar una nueva orden de consumo para una mesa.");
        administradorPermisos.registrarNuevopermiso(permisoTemporal);
        
        permisoTemporal = new Permisos();
        permisoTemporal.setSlugPermisos("pedido.editar");
        permisoTemporal.setNombrePermiso("editar pedidos");
        permisoTemporal.setDescripcionPermiso("permite modifica productos agregados, de un pedido activo.");
        administradorPermisos.registrarNuevopermiso(permisoTemporal);
        
        permisoTemporal = new Permisos();
        permisoTemporal.setSlugPermisos("pedido.visualizar");
        permisoTemporal.setNombrePermiso("visualizar pedido");
        permisoTemporal.setDescripcionPermiso("permitir ver la lista y el estado de todos los pedidos al dia");
        administradorPermisos.registrarNuevopermiso(permisoTemporal);
        
        //seccion control de mesas
        
        permisoTemporal = new Permisos();
        permisoTemporal.setSlugPermisos("mesa.gestionar");
        permisoTemporal.setDescripcionPermiso("gestionar mesas");
        permisoTemporal.setDescripcionPermiso("permite cambiar el estado de las mesas (ocpada, disponible, mantenimiento, reservada)");
        administradorPermisos.registrarNuevopermiso(permisoTemporal);
        
        //seccion control de carta. 
        
        permisoTemporal = new Permisos();
        permisoTemporal.setSlugPermisos("producto.gestionar");
        permisoTemporal.setNombrePermiso("gestionar productos");
        permisoTemporal.setDescripcionPermiso("permite crear modificar precios o dar debaja los menu de temporadas");
        administradorPermisos.registrarNuevopermiso(permisoTemporal);
        
        //seccion control de caja y facturacion
        
        permisoTemporal = new Permisos();
        permisoTemporal.setSlugPermisos("factura.crear");
        permisoTemporal.setNombrePermiso("crear factura");
        permisoTemporal.setDescripcionPermiso("permite generar el ticket con el total de cobro");
        administradorPermisos.registrarNuevopermiso(permisoTemporal);
        
        
        
        
    }
    
}
