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
@WebService(serviceName = "WEliminarGrupo")
public class WEliminarGrupo {

    /**
     * Web service
     * @param Grupo id del Grupo
     */
    @WebMethod(operationName = "EliminarGrupo")
    public String EliminarGrupo(@WebParam(name = "Grupo") String Grupo) {
        String mensaje;
        try {
             //Inicializo la bd
            Database2 db = new Database2();
            //Combierto la id a int
            int id = Integer.parseInt(Grupo);
            //Borro el grupo
            db.eliminarGrupo(id);
            mensaje = "Grupo eliminado";
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " :'v");
            mensaje = "Error eliminado grupo";
        }
        return mensaje;
    }
}
