/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import BaseDatos.Database2;
import Objetos.Grupo;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Carlos
 */
@WebService(serviceName = "WCrearGrupos")
public class WCrearGrupos {

    /**
     * sWeb service
     * @param nomNuevoGrupo El nombre del nuevo grupo
     * @param lider Nombre del usuario que crea el nuevo grupo
     * @param miembros Concatenacion de los miembros asignados
     */
    @WebMethod(operationName = "CrearGrupos")
    public String CrearGrupos(@WebParam(name = "nomNuevoGrupo") String nomNuevoGrupo, @WebParam(name = "lider") String lider, @WebParam(name = "miembros") String miembros) {
        String mensaje;
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Inicializo el objeto grupo
            Grupo g = new Grupo();
            
            /*Parametros que usare para ingresar datos a la db desde el objeto*/
            //Array de strings a array de int's
            String[] Amiembros = miembros.split(",");
            int[] IDMiembros = new int[Amiembros.length];
            int i = 0;
            for(String miembro:Amiembros)
            {
                IDMiembros[i] = db.IdentificarUsuario(miembro);//Las id's se traen la la bd
                i++;
            }
            int IDLider = db.IdentificarUsuario(lider);//Obtengo la id del lider
            
            //Meto los datos a mi objeto
            g.setIDLider(IDLider);
            g.setNombreGrupo(nomNuevoGrupo);
            g.setMiembros(IDMiembros);
            
            //Agrego el grupo a la bd
            db.crearGrupo(g);
            mensaje = "Grupo creado";
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
            mensaje = "Error creando grupo";
        }
        return mensaje;
    }
}
