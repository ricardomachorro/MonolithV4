package WebService;

import BaseDatos.Database2;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Carlos
 */
@WebService(serviceName = "WAbandonarGrupo")
public class WAbandonarGrupo {

    /**
     * Metodo para abandonar grupo  
     * Recibe la id del grupo a abandonar y la id del usuario que va a abandonar el grupo
     */
    @WebMethod(operationName = "AbandonarGrupo")
    public String AbandonarGrupo(@WebParam(name = "Grupo") String Grupo, @WebParam(name = "Usuario") String Usuario) {
        String mensaje;
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Combierto las id's a int
            int id = Integer.parseInt(Grupo);
            int usu = Integer.parseInt(Usuario);
            //Borro al miembro
            db.abandonarGrupo(id, usu);
            mensaje = "Miembro borrado";
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " :'v");
            mensaje = "Error eliminando miembro";
        }
        return mensaje;
    }
}
