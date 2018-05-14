/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import BaseDatos.Database2;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Carlos
 */
@WebService(serviceName = "WEstadoTarea")
public class WEstadoTarea {

    /**
     * Web service
     * @param Estado Es el estado con al que se cambio, 1 si esta checada y 2 si no
     * @param Tarea Id de la tarea al que se le cambio el estado
     * 
     */
    @WebMethod(operationName = "EstadoTarea")
    public String EstadoTarea(@WebParam(name = "Estado") String Estado, @WebParam(name = "Tarea") String Tarea) {
        String mensaje;
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            
            //Cambio las variables
            int e = Integer.parseInt(Estado);
            int id = Integer.parseInt(Tarea);
            
            db.CambiarEstadoTarea(id, e);
            
            mensaje = "Estado de tarea cambiado";
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            mensaje = "Error cambiando el estado de la tarea";
        }
        return mensaje;
    }
}
