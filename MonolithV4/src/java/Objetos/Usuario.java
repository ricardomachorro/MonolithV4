package Objetos;

import org.json.simple.JSONObject;

public class Usuario {
    
    private String Nombre;
    private String Correo;
    private String Password;
    private int Edad;
    private String Pais;
    private String Direccion;
    private int Puntos;
    private String Validado;

    public int getPuntos() {
        return Puntos;
    }

    public void setPuntos(int Puntos) {
        this.Puntos = Puntos;
    }
    
    
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
     public String getValidado() {
        return Validado;
    }

    public void setValidado(String Validado) {
        this.Validado = Validado;
    }
    
    public JSONObject obtenerJSONUsuario(int IDUsuario, String NombreUsuario, String Correo, int Edad, String Pais, String Direccion, String Contrasena, int TipoUsuario, String Validado, int Puntos){        
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

}

