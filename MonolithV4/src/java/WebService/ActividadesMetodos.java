/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
import Objetos.Actividad;
/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "ActividadesMetodos")
public class ActividadesMetodos {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "IngresarActividad")
    public void hello(@WebParam(name = "NombreActividad") String NombreActividad, 
           @WebParam(name = "UsuarioNombre") String NombreUsuario,
           @WebParam(name = "NombreCategoria") String NombreCategoria
           ) {
        try{
            Database2 db=new Database2();
            Actividad act=new Actividad();
            act.setTitulo(NombreActividad);
            act.setUsuario(NombreUsuario);
            act.setCategoria(NombreCategoria);
            db.IngresoActividad(act);
        }catch(Exception ex){
            
        }
        
        
    }
    
    
}
