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
@WebService(serviceName = "WEliminarTarea")
public class WEliminarTarea {

    /**
     * Web Service
     * @param idTarea Id de la tarea a eliminar
     */
    @WebMethod(operationName = "EliminarTarea")
    public String EliminarTarea(@WebParam(name = "idTarea") String idTarea) {
        String mensaje;
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Combierto la id a int
            int id = Integer.parseInt(idTarea);
            //Borro la tarea
            db.eliminarTarea(id);
            mensaje = "Tarea eliminada";
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
            mensaje = "Error eliminando tarea";
        }
        return mensaje;
    }
}
