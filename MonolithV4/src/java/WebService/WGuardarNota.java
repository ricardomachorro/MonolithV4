/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import BaseDatos.Database2;
import Objetos.Nota;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Carlos
 */
@WebService(serviceName = "WGuardarNota")
public class WGuardarNota {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "GuardarNota")
    public String GuardarNota(@WebParam(name = "usuario") String usuario, @WebParam(name = "TitleNota") String TitleNota, @WebParam(name = "ContenidoNota") String ContenidoNota) {
        String mensaje;
        try {
            Database2 db = new Database2();
            Nota note = new Nota();

            note.setUsuario(usuario);
            note.setTitulo(TitleNota);
            note.setContenido(ContenidoNota);
            
            db.IngresarNota(note);
            
            mensaje = "Nota guardada";
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            mensaje = "Error guardando nota";
        }
        return mensaje;
    }
}
