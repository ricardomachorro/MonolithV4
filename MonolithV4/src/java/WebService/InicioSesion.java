/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import BaseDatos.Database2;
import Objetos.ConversorJson;
import Objetos.ObtenerDatos;
import Objetos.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Alumno
 */
@WebService(serviceName = "InicioSesion")
public class InicioSesion {

    /**
     * This is a sample web service operation
     *
     * @param txt
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "validarUsuarioAndroid")
    public String validarUsuarioAndroid(@WebParam(name = "numero") String numero) {

        ObtenerDatos objeto;
        try {
            objeto = new ObtenerDatos();

            String mensaje;
            String usr;
            String psw;
            int IDUsuario;
            String NombreUsuario;
            String Correo;
            int Edad;
            String Pais;
            String Direccion;
            String Contrasena;
            int TipoUsuario;
            String Validado;
            int Puntos;

            try {
                JSONParser parser = new JSONParser();
                JSONObject info = (JSONObject) parser.parse(numero);
                usr = (String) info.get("usuario");
                psw = (String) info.get("contrasena");
            } catch (ParseException error) {
                return "error";
            }
            Usuario u = new Usuario();
            try {
                Database2 db = new Database2();
                u.setNombre(usr);
                u.setPassword(psw);
                if (db.IngresoPrograma(u)) {
                    ConversorJson obj = new ConversorJson();
                    IDUsuario = objeto.getIDUsuario(usr);
                    NombreUsuario = objeto.getNombreUsuario(usr);
                    Correo = objeto.getCorreo(usr);
                    Edad = objeto.getEdad(usr);
                    Pais = objeto.getPais(usr);
                    Direccion = objeto.getDireccion(usr);
                    Contrasena = objeto.getContrasena(usr);
                    TipoUsuario = objeto.getTipoUsuario(usr);
                    Validado = objeto.getValidado(usr);
                    Puntos = objeto.getPuntos(usr);
                    JSONObject datos = obj.obtenerJSON(IDUsuario, NombreUsuario, Correo, Edad, Pais, Direccion, Contrasena, TipoUsuario, Validado, Puntos);
                    mensaje = datos.toString();

                } else {
                    return "incorrecto";
                }

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
                return "error";
            }

            return mensaje;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
