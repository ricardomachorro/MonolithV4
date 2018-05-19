package com.example.alumno.monolithmovil;

public class ActividadesCartas {

    String NombreAct,Fecha,Categoria;
    int Identificador;


    public ActividadesCartas(String nombreAct, String fecha, String categoria) {
        NombreAct = nombreAct;
        Fecha = fecha;
        Categoria = categoria;
    }

    public String getNombreAct() {
        return NombreAct;
    }

    public void setNombreAct(String nombreAct) {
        NombreAct = nombreAct;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int identificador) {
        Identificador = identificador;
    }

}
