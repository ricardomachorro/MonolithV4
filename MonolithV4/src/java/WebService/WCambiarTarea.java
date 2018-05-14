/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import BaseDatos.Database2;
import Objetos.Tarea;
import java.sql.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Carlos
 */
@WebService(serviceName = "WCambiarTarea")
public class WCambiarTarea {

    /**
     * Web service para cambiar los datos de una tarea
     * @param idTarea Id de la tarea a cambiar
     * @param newNombre El nuevo nombre de la tarea
     * @param newMiembro El nombre de usaurio de el nuevo miembro a asignar
     * @param newFecha La nueva fecha asiganada a la tarea
     * @param byeMiembro El nombre del miembro que se va adesasignar
     * 
     */
    @WebMethod(operationName = "CambiarTarea")
    public String CambiarTarea(@WebParam(name = "idTarea") String idTarea, @WebParam(name = "newNombre") String newNombre, 
            @WebParam(name = "newMiembro") String newMiembro, @WebParam(name = "newFecha") String newFecha, @WebParam(name = "byeMiembro") String byeMiembro) {
        String mensaje;
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Inicializo el objeto tarea
            Tarea t = new Tarea();
            
            //Datos faltantes por convertir
            int IDT = Integer.parseInt(idTarea);
            Date fechaT = Date.valueOf(newFecha);
            int IDM;
            
            //Lleno el objeto tarea
            t.setIdTarea(IDT);
            t.setNombreTarea(newNombre);
            t.setFechaTarea(fechaT);
            
            db.actualizarTarea(t);
            
            if(newMiembro.equals("Asignar miembro")) {
                IDM = db.IdentificarUsuario(byeMiembro);
                db.desasignarMiembro(IDT, IDM);
            } else if(byeMiembro.equals("Desasignar miembro")) {
                IDM = db.IdentificarUsuario(newMiembro);
                db.asignarMiembro(IDT, IDM);
            }
            
            mensaje = "Tarea cambiada";
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            mensaje = "Error cambiando tarea";
        }
        return mensaje;
    }
}
