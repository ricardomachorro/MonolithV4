package com.example.alumno.monolithmovil.adapters;

public class CategoriasCartasAct {
    int Identificador;
    String Nombre;

    public CategoriasCartasAct(String nombre,int identificador) {
        this.Nombre = nombre;
        this.Identificador=identificador;
    }

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int identificador) {
        Identificador = identificador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


}
