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
@WebService(serviceName = "WPonerNota")
public class WPonerNota {

    /**
     * Web service para traer un elemento nota
     * @param usuario El nombre del usuario propietario
     * @param NombreElemento El titulo de la nota
     * @return El contenido de la nota
     * 
     */
    @WebMethod(operationName = "PonerNota")
    public String PonerNota(@WebParam(name = "usuario") String usuario, @WebParam(name = "NombreElemento") String NombreElemento) {
        String Contenido;
        try {
            Database2 db=new Database2();
            Contenido=db.NotaContenido(usuario, NombreElemento);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            Contenido = "";
        }
        return Contenido;
    }
}
