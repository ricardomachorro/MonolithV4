
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
import Objetos.Actividad;
import Objetos.ObtenerDatos;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebService(serviceName = "ActividadWebMethods")
public class ActividadWebMethods {

    @WebMethod(operationName = "RegistroActividad")
    public String RegistroActividad(@WebParam(name = "NombreUsuario") String NombreUsuario, @WebParam(name = "ClaseActividad") String ClaseActividad,
            @WebParam(name = "TituloActividad") String TituloActividad) throws Exception {
        String Exito = "Exito";
        try{
        int IDActividad=0;
        Actividad act = new Actividad();
        act.setCategoria(ClaseActividad);
        act.setUsuario(NombreUsuario);
        act.setTitulo(TituloActividad);
        Database2 db = new Database2();
       IDActividad=db.IngresoActividad(act);
       if(IDActividad>0){
          
           
       }
       }catch(Exception ex){
           Exito="Erro";
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
    
    @WebMethod(operationName = "ConsultarAct")
    public String ConsultarAct(@WebParam(name = "UsuarioID") String UsuarioID) throws Exception {
       String PaqueteAct="";
        ObtenerDatos objeto;
        try{
             objeto = new ObtenerDatos();
             Database2 db=new Database2();
              ArrayList<Actividad> PaqueteActividad=db.ConsultarActividad(UsuarioID);
              JSONObject PaqueteFinalJson=new JSONObject();
              JSONArray PaqueteJsonAct = new JSONArray();
               
              for(int i=0;i<PaqueteActividad.size();i++){
                  JSONObject ObjetoJson= new JSONObject();
                  ObjetoJson.put("Nombre", PaqueteActividad.get(i).getTitulo());
                  ObjetoJson.put("Categoria",PaqueteActividad.get(i).getCategoria());
                  ObjetoJson.put("Fecha",PaqueteActividad.get(i).getFechaLimite().toString());
                  ObjetoJson.put("Estado",PaqueteActividad.get(i).getEstado());
                  PaqueteJsonAct.add(ObjetoJson);
              }
             
               PaqueteFinalJson.put("Actividades",PaqueteJsonAct);
               PaqueteAct=PaqueteFinalJson.toString();
             
             
        }catch(Exception ex){
            PaqueteAct="Error";
        }
       return PaqueteAct;
    }
    
     @WebMethod(operationName = "ConsultarActNoFin")
    public String ConsultarActNoFin(@WebParam(name = "UsuarioID") String UsuarioID) throws Exception {
       String PaqueteAct="";
        ObtenerDatos objeto;
        try{
             objeto = new ObtenerDatos();
             Database2 db=new Database2();
              ArrayList<Actividad> PaqueteActividad=db.ConsultarActividad(UsuarioID);
              JSONObject PaqueteFinalJson=new JSONObject();
              JSONArray PaqueteJsonAct = new JSONArray();
               
              for(int i=0;i<PaqueteActividad.size()-1;i++){
                  JSONObject ObjetoJson= new JSONObject();
                  ObjetoJson.put("Nombre", PaqueteActividad.get(i).getTitulo());
                  ObjetoJson.put("Categoria",PaqueteActividad.get(i).getCategoria());
                  ObjetoJson.put("Fecha",PaqueteActividad.get(i).getFechaLimite().toString());
                  ObjetoJson.put("Estado",PaqueteActividad.get(i).getEstado());
                  PaqueteJsonAct.add(ObjetoJson);
              }
             
               PaqueteFinalJson.put("Actividades",PaqueteJsonAct);
               PaqueteAct=PaqueteFinalJson.toString();
             
             
        }catch(Exception ex){
            PaqueteAct="Error";
        }
       return PaqueteAct;
    }
    
    
     @WebMethod(operationName = "ConsultarCat")
    public String ConsultarCat(@WebParam(name = "UsuarioID") String UsuarioID) throws Exception {
       String PaqueteAct="";
        ObtenerDatos objeto;
        try{
             objeto = new ObtenerDatos();
             Database2 db=new Database2();
              ArrayList<Actividad> PaqueteActividad=db.ConsultarCategorias(UsuarioID);
              JSONObject PaqueteFinalJson=new JSONObject();
              JSONArray PaqueteJsonAct = new JSONArray();
               
              for(int i=0;i<PaqueteActividad.size();i++){
                  JSONObject ObjetoJson= new JSONObject();
                  ObjetoJson.put("Categoria",PaqueteActividad.get(i).getCategoria());
                  PaqueteJsonAct.add(ObjetoJson);
              }
             
               PaqueteFinalJson.put("Actividades",PaqueteJsonAct);
               PaqueteAct=PaqueteFinalJson.toString();
             
             
        }catch(Exception ex){
            PaqueteAct="Error";
        }
       return PaqueteAct;
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
