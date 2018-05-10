
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
import Objetos.Usuario;
import Seguridad.Cifrados;
import Objetos.ConversorJson;
import Objetos.ObtenerDatos;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebService(serviceName = "UsuarioWebMethods")
public class UsuarioWebMethods {

    
    @WebMethod(operationName = "RegistroUsuario")
    public boolean RegistroUsuario(@WebParam(name = "NombreUsuario") String NombreUsuario,@WebParam(name = "DirecUsuario") String DirecUsuario,
            @WebParam(name = "EdadUsuario") String EdadUsuario,@WebParam(name = "PaisUsuario") String PaisUsuario,
            @WebParam(name = "CorreoUsuario") String CorreoUsuario,@WebParam(name = "ContraUsuario") String ContraUsuario) throws Exception{
        boolean Exito=false;
        Database2 db=new Database2();
        Usuario user=new Usuario();
        user.setNombre(NombreUsuario);
        user.setCorreo(CorreoUsuario);
        user.setPuntos(0);
        user.setDireccion(DirecUsuario);
        user.setEdad(Integer.parseInt(EdadUsuario));
        user.setPassword(ContraUsuario);
        user.setPais(PaisUsuario);
        if(db.IngresoUsuario(user)){
            Exito=true;
        }
       
        
        return Exito;
    }
    
    
    @WebMethod(operationName = "RegistroUsuarioMovil")
    public String RegistroUsuarioMovil(@WebParam(name = "Usuario") String Usuario) throws Exception{
        String mensaje="";
        Database2 db=new Database2();
        Usuario user=new Usuario();
        JSONParser parser = new JSONParser();
        JSONObject info = (JSONObject) parser.parse(Usuario);
        String NombreUsuario=(String)info.get("NombreUsuario");
        String CorreoUsuario=(String)info.get("CorreoUsuario");
        String EdadUsuario=(String)info.get("EdadUsuario");
        String DirecUsuario=(String)info.get("DirecUsuario");
        String PaisUsuario=(String)info.get("PaisUsuario");
        String ContraUsuario=(String)info.get("ContraUsuario");
        user.setNombre(NombreUsuario);
        user.setCorreo(CorreoUsuario);
        user.setPuntos(0);
        user.setDireccion(DirecUsuario);
        user.setEdad(Integer.parseInt(EdadUsuario));
        user.setPassword(ContraUsuario);
        user.setPais(PaisUsuario);
        user.setValidado("Si");
        
        /*
        if(db.UsuarioValidado(user.getNombre())){
            String validad="Si";
        }*/
   
        if(db.IngresoUsuario(user)){
            ObtenerDatos objeto;
            objeto = new ObtenerDatos();
            String usr;
            String psw;
            int IDUsuario;
            String NombreUser;
            String Correo;
            int Edad;
            String Pais;
            String Direccion;
            String Contrasena;
            int TipeUsuario;
            String Validado;
            int Points;
             ConversorJson obj = new ConversorJson();
                    IDUsuario = objeto.getIDUsuario(user.getNombre());
                    NombreUser = objeto.getNombreUsuario(user.getNombre());
                    Correo = objeto.getCorreo(user.getNombre());
                    Edad = objeto.getEdad(user.getNombre());
                    Pais = objeto.getPais(user.getNombre());
                    Direccion = objeto.getDireccion(user.getNombre());
                    Contrasena = objeto.getContrasena(user.getNombre());
                    TipeUsuario = objeto.getTipoUsuario(user.getNombre());
                    Validado = objeto.getValidado(user.getNombre());
                    Points = objeto.getPuntos(user.getNombre());
                   JSONObject datos = obj.obtenerJSON(IDUsuario, NombreUser, Correo, Edad, Pais, Direccion, Contrasena, TipeUsuario, Validado, Points);
                    mensaje = datos.toString();
        }else if(db.UsuarioRepetido(user)){
            mensaje= "Repetido";
        }else{
            mensaje="Error";
        }
       
      
        return mensaje;
    }
    
    
    
    @WebMethod(operationName = "ValidacionUsuario")
    public boolean ValidacionUsuario(@WebParam(name = "NombreUsuario") String NombreUsuario,@WebParam(name = "CodigoUsuario") String CodigoUsuario,
            @WebParam(name = "ContraUsuario") String ContraUsuario) throws Exception{
        boolean Exito=false;
        Database2 db=new Database2();
        String ParametrosHash = NombreUsuario + ContraUsuario;
       int HashCode = ParametrosHash.hashCode();
        int adendum;
        if (HashCode < 0) {
            adendum = HashCode*-1;
        } else {
            adendum = HashCode;
        }
        
        if(db.ResultadoFinalValidacion(NombreUsuario).equalsIgnoreCase(CodigoUsuario) && Integer.toString(adendum).equalsIgnoreCase(CodigoUsuario)){
            db.DarValidoUsuario(NombreUsuario);
        }
        
        return Exito;
    }
    
    
}
