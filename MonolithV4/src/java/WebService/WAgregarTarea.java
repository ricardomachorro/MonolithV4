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
@WebService(serviceName = "WAgregarTarea")
public class WAgregarTarea {

    /**
     * Web service que agrega una tarea alv
     * @param nomTarea Trae el nombre de la tarea ingreado por el usuario
     * @param nomGrupo Trae el nombre del grupo al que se le va a crear la tarea
     * @param fechaTarea Trae la fecha lejiga para la tarea alv
     * @param nomMiembro Trae el nombre del miembro asignado a la tarea
     * 
     */
    @WebMethod(operationName = "AgregarTarea")
    public String AgregarTarea(@WebParam(name = "nomTarea") String nomTarea, @WebParam(name = "nomGrupo") String nomGrupo,
            @WebParam(name = "fechaTarea") String fechaTarea, @WebParam(name = "nomMiembro") String nomMiembro) {
        String mensaje;
        //Datos faltantes por convertir
        int idGrupo;
        int idMiembro;
        Date fechaT;
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Inicializo el objeto tarea
            Tarea t = new Tarea();

            //Convierto la fecha a date de sql
            fechaT = Date.valueOf(fechaTarea);
            //Combierto el nombre del grupo a la id del grupo para el insert alv
            idGrupo = db.IDGrupo(nomGrupo);
            //Traigo la id del miembro para asignarlo a la tarea
            idMiembro = db.IdentificarUsuario(nomMiembro);
            
            //Lleno el objeto tarea
            t.setNombreTarea(nomTarea);
            t.setIdGrupoTarea(idGrupo);
            t.setFechaTarea(fechaT);
            t.setIdMiembroTarea(idMiembro);
            
            //Creo la tarea (Al mismo tiempo se le asigna el miembro)
            db.crearTarea(t);
            mensaje = "Tarea creada";
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
            mensaje = "Error creando tarea";
        }
        return mensaje;
    }
}
