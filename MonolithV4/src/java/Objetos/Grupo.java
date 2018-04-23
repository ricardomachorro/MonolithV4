package Objetos;

/**
 *
 * @author Alumno
 */
public class Grupo {
    private String nombreGrupo;
    private String[] nomTareas;
    private int[] miembros;
    private int IDLider;

    public int getIDLider() {
        return IDLider;
    }

    public void setIDLider(int IDLider) {
        this.IDLider = IDLider;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public int[] getMiembros() {
        return miembros;
    }

    public void setMiembros(int[] miembros) {
        this.miembros = miembros;
    }

    public String[] getNomTareas() {
        return nomTareas;
    }

    public void setNomTareas(String[] nomTareas) {
        this.nomTareas = nomTareas;
    }
}
