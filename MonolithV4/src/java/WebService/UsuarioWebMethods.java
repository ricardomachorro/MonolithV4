
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
import Objetos.Usuario;
import Seguridad.Cifrados;
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
        String validado="No";
        int TipoUsuario=0;
        int Puntos=0;
        if(db.UsuarioValidado(user.getNombre())){
            String validad="Si";
        }
        Puntos=db.PuntosUsuario(Usuario);
        TipoUsuario=db.TipoUsuario(user);
        if(db.IngresoUsuario(user)){
                    JSONObject datos = user.obtenerJSONUsuario(db.IdentificarUsuario(user.getNombre()), user.getNombre(),user.getCorreo(), user.getEdad(),user.getPais(), user.getDireccion(), user.getPassword(),TipoUsuario,validado,Puntos);   
                    mensaje = datos.toString();
        }else if(db.UsuarioRepetido(user)){
            return "Repetido";
        }else{
            return "Error";
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
