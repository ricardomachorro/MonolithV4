package com.example.alumno.monolithmovil;

/**
 * Created by Alex on 30/04/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Sesion {

    private SharedPreferences datosSesion;

    public Sesion(Context contexto) {
        datosSesion = PreferenceManager.getDefaultSharedPreferences(contexto);
    }

    public void setDatos(int IDUsuario, String NombreUsuario, String Correo, int Edad, String Pais, String Direccion, String Contrasena, int TipoUsuario, String Validado, int Puntos) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putInt("IDUsuario", IDUsuario);
        usr.putString("NombreUsuario", NombreUsuario);
        usr.putString("Correo", Correo);
        usr.putInt("Edad", Edad);
        usr.putString("Pais", Pais);
        usr.putString("Direccion", Direccion);
        usr.putString("Contrasena", Contrasena);
        usr.putInt("TipoUsuario", TipoUsuario);
        usr.putString("Validado", Validado);
        usr.putInt("Puntos", Puntos);

        usr.apply();
    }

    public void clearDatos() {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putInt("IDUsuario", 0);
        usr.putString("NombreUsuario", "");
        usr.putString("Correo", "");
        usr.putInt("Edad", 0);
        usr.putString("Pais", "");
        usr.putString("Direccion", "");
        usr.putString("Contrasena", "");
        usr.putInt("TipoUsuario", 0);
        usr.putString("Validado", "");
        usr.putInt("Puntos", 0);

        usr.apply();
    }

    public void setIDUsuario(int IDUsuario) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putInt("IDUsuario", IDUsuario);
        usr.apply();
    }

    public void setNombreUsuario(String NombreUsuario) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putString("NombreUsuario", NombreUsuario);
        usr.apply();
    }

    public void setCorreo(String Correo) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putString("Correo", Correo);
        usr.apply();
    }

    public void setEdad(int Edad) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putInt("Edad", Edad);
        usr.apply();
    }

    public void setPais(String Pais) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putString("Pais", Pais);
        usr.apply();
    }

    public void setDireccion(String Direccion) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putString("Direccion", Direccion);
        usr.apply();
    }

    public void setContrasena(String Contrasena) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putString("Contrasena", Contrasena);
        usr.apply();
    }

    public void setTipoUsuario(int TipoUsuario) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putInt("TipoUsuario", TipoUsuario);
        usr.apply();
    }

    public void setValidado(String Validado) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putString("Validado", Validado);
        usr.apply();
    }

    public void setPuntos(int Puntos) {
        SharedPreferences.Editor usr = datosSesion.edit();
        usr.putInt("Puntos", Puntos);
        usr.apply();
    }


    public int getIDUsuario() {
        int IDUsuario = datosSesion.getInt("IDUsuario", 0);
        return IDUsuario;
    }

    public String getNombreUsuario() {
        String NombreUsuario = datosSesion.getString("NombreUsuario","");
        return NombreUsuario;
    }

    public String getCorreo() {
        String Correo = datosSesion.getString("Correo","");
        return Correo;
    }

    public int getEdad() {
        int Edad = datosSesion.getInt("Edad", 0);
        return Edad;
    }

    public String getPais() {
        String Pais = datosSesion.getString("Pais", "");
        return Pais;
    }

    public String getDireccion() {
        String Direccion = datosSesion.getString("Direccion", "");
        return Direccion;
    }

    public String getContrasena() {
        String Contrasena = datosSesion.getString("Contrasena", "");
        return Contrasena;
    }

    public int getTipoUsuario() {
        int TipoUsuario = datosSesion.getInt("TipoUsuario", 0);
        return TipoUsuario;
    }

    public String getValidado() {
        String Validado = datosSesion.getString("Validado", "");
        return Validado;
    }

    public int getPuntos() {
        int Puntos = datosSesion.getInt("Puntos", 0);
        return Puntos;
    }
}
