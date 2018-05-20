package com.example.alumno.monolithmovil;

public class ActividadesCartas {

    String NombreAct,Fecha,Categoria,Estado;
    int Identificador;

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public ActividadesCartas(String nombreAct, String fecha, String categoria,String estado, int identificador) {
        NombreAct = nombreAct;
        Fecha = fecha;
        Categoria = categoria;
        Identificador = identificador;
        Estado=estado;
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
