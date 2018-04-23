package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
import Objetos.Actividad;

@WebService(serviceName = "ActividadWebMethods")
public class ActividadWebMethods {

    @WebMethod(operationName = "RegistroActividad")
    public boolean RegistroActividad(@WebParam(name = "NombreUsuario") String NombreUsuario, @WebParam(name = "ClaseActividad") String ClaseActividad,
            @WebParam(name = "TituloActividad") String TituloActividad) throws Exception {
        boolean Exito = false;
        int IDActividad=0;
        Actividad act = new Actividad();
        act.setCategoria(ClaseActividad);
        act.setUsuario(NombreUsuario);
        act.setTitulo(TituloActividad);
        Database2 db = new Database2();
       IDActividad=db.IngresoActividad(act);
       if(IDActividad>0){
           Exito=true;
       }
 
        return Exito;
    }
    
     @WebMethod(operationName = "CambiarActividad")
    public boolean CambiarActividad(@WebParam(name = "NombreUsuario") String NombreUsuario,@WebParam(name = "IDActividad") int IDActividad, @WebParam(name = "ClaseActividad") String ClaseActividad,
            @WebParam(name = "TituloActividad") String TituloActividad,@WebParam(name = "FechaActividad") String FechaActividad) throws Exception {
        boolean Exito = false;
        
        Database2 db = new Database2();
         if(db.ActualizacionActividad(IDActividad,TituloActividad, ClaseActividad,FechaActividad,NombreUsuario)){
             Exito=true;
         }
   
        return Exito;
    }
    
    @WebMethod(operationName = "EliminarActividad")
    public boolean EliminarActividad(@WebParam(name = "IDActividad") int IDActividad) throws Exception {
        boolean Exito = false;
        
        Database2 db = new Database2();
        if(db.EliminarActividad(IDActividad)){
            Exito=true;
        }
        return Exito;
    }
    
    @WebMethod(operationName = "EliminarCategoria")
    public boolean EliminarCategoria(@WebParam(name = "UsuarioNombre") String UsuarioNombre,@WebParam(name = "CategoriaNombre") String CategoriaNombre) throws Exception {
        boolean Exito = false;
        
        Database2 db = new Database2();
        if(db.EliminarCategoria(UsuarioNombre, CategoriaNombre)){
            Exito=true;
        }
        return Exito;
    }
}
