
package Objetos;

import org.json.simple.JSONObject;

/**
 *
 * @author Alex
 */
public class ConversorJson {
   
    
    public JSONObject obtenerJSON(int IDUsuario, String NombreUsuario, String Correo, int Edad, String Pais, String Direccion, String Contrasena, int TipoUsuario, String Validado, int Puntos){        
        JSONObject resultado = new JSONObject();
        resultado.put("IDUsuario", IDUsuario);
        resultado.put("NombreUsuario", NombreUsuario);
        resultado.put("Correo", Correo);
        resultado.put("Edad", Edad);
        resultado.put("Pais", Pais);
        resultado.put("Direccion", Direccion);
        resultado.put("Contrasena", Contrasena);
        resultado.put("TipoUsuario", TipoUsuario);
        resultado.put("Validado", Validado);
        resultado.put("Puntos", Puntos);
        
        return resultado;
    }
    public JSONObject ConfigurarJSON(String NombreUsuario, int Edad, String Pais, String Direccion, String Correo, String Contrasena){        
        JSONObject resultado = new JSONObject();
        resultado.put("NombreUsuario", NombreUsuario);
        resultado.put("Edad", Edad);
        resultado.put("Pais", Pais);
        resultado.put("Direccion", Direccion);
        resultado.put("Correo", Correo);
        resultado.put("Contrasena", Contrasena);
        return resultado;
    }
    
    
}
