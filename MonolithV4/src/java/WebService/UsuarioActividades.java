/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
import Objetos.Usuario;
/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "Usuario")
public class UsuarioActividades {

    @WebMethod(operationName = "Ingresar")
    public boolean IngresarUsuario (@WebParam(name = "NombreUsuario") String NombreUsuario,
            @WebParam(name = "EdadUsuario") int EdadUsuario,@WebParam(name = "Pais") String Pais,
            @WebParam(name = "DireccionUsuario") String DireccionUsuario, 
            @WebParam(name = "CorreoUsuario") String CorreoUsuario,
            @WebParam(name = "PasswordUsaurio") String PasswordUsuario)  {
        
        boolean Exito=false;
       try{
           Database2 db=new Database2();
           Usuario user=new Usuario();   
           user.setNombre(NombreUsuario);
           user.setEdad(EdadUsuario);
           user.setDireccion(DireccionUsuario);
           user.setPais(Pais);
           user.setCorreo(CorreoUsuario);
           user.setPassword(PasswordUsuario);
           db.IngresoUsuario(user);
           Exito=true;
       }catch(Exception ex){
           
       }
       return Exito;
    }
}
