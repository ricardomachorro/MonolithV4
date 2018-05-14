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
@WebService(serviceName = "WActualizarNota")
public class WActualizarNota {

    /**
     * Web service para actualizar una nota
     * @param Usuario Nombre del usaurio propietario de la nota
     * @param TituloNota Titulo nuevo de la nota
     * @param ContenidoNota El nuevo contenido de la nota
     * @param Anterior El titulo anterior de la nota
     * @return mensaje que indica si se tuvo exito o nel
     */
    @WebMethod(operationName = "ActualizarNota")
    public String ActualizarNota(@WebParam(name = "Usuario") String Usuario, @WebParam(name = "TituloNota") String TituloNota, 
            @WebParam(name = "ContenidoNota") String ContenidoNota, @WebParam(name = "Anterior") String Anterior) {
        String mensaje;
        try {
            Database2 db = new Database2();
            Nota nota = new Nota();
            
            nota.setUsuario(Usuario);
            nota.setTitulo(TituloNota);
            nota.setContenido(ContenidoNota);
            
            db.ActualizarNota(nota, Anterior);
            mensaje = "Nota actualizada";
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            mensaje = "Error actualizando nota";
        }
        return mensaje;
    }
}
