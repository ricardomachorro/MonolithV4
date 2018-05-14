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
@WebService(serviceName = "WAgregarMiembro")
public class WAgregarMiembro {

    /**
     * Web Service que regresa el nombre de un usuario para crear un grupo
     * @param correoMiembro Se debe ingresar el correo del miembro a agregar
     * @return nombre del miembro o vacio para evaluar el error
     */
    @WebMethod(operationName = "AgregarMiembro")
    public String AgregarMiembro(@WebParam(name = "correoMiembro") String correoMiembro) {
        String nombreMiembro;
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Traigo el nombre del usuario de la bd buscandolo por su correo
            nombreMiembro = db.consultarMiembro(correoMiembro);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " :'v");
            nombreMiembro = "";
        }
        //Envio el nombre del usuario/miembro o el error alv
        return nombreMiembro;
    }
}
