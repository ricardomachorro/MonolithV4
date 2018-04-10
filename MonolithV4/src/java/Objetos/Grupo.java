/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Alumno
 */
public class Grupo {
    private String nombreGrupo;
    private String[] miembros;
    private String[] nomTareas;

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String[] getMiembros() {
        return miembros;
    }

    public void setMiembros(String[] miembros) {
        this.miembros = miembros;
    }

    public String[] getNomTareas() {
        return nomTareas;
    }

    public void setNomTareas(String[] nomTareas) {
        this.nomTareas = nomTareas;
    }
}
