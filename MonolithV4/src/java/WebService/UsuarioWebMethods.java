
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
import Objetos.Usuario;
import Seguridad.Cifrados;


@WebService(serviceName = "UsuarioWebMethods")
public class UsuarioWebMethods {

    
    @WebMethod(operationName = "RegistroUsuario")
    public boolean RegistroUsuario(@WebParam(name = "NombreUsuario") String NombreUsuario,@WebParam(name = "DirecUsuario") String DirecUsuario,
            @WebParam(name = "EdadUsuario") int EdadUsuario,@WebParam(name = "PaisUsuario") String PaisUsuario,
            @WebParam(name = "CorreoUsuario") String CorreoUsuario,@WebParam(name = "ContraUsuario") String ContraUsuario) throws Exception{
        boolean Exito=false;
        Database2 db=new Database2();
        Usuario user=new Usuario();
        user.setNombre(NombreUsuario);
        user.setCorreo(CorreoUsuario);
        user.setPuntos(0);
        user.setDireccion(DirecUsuario);
        user.setEdad(EdadUsuario);
        user.setPassword(ContraUsuario);
        user.setPais(PaisUsuario);
        if(db.IngresoUsuario(user)){
            Exito=true;
        }
       
        
        return Exito;
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
