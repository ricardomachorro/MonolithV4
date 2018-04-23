package Objetos;

import java.sql.Date;

/**
 *
 * @author Carlos
 */
public class Tarea {
    private String nombreTarea;
    private int idTarea;
    private int IdGrupoTarea;
    private Date fechaTarea;
    private int idMiembroTarea;

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public int getIdGrupoTarea() {
        return IdGrupoTarea;
    }

    public void setIdGrupoTarea(int IdGrupoTarea) {
        this.IdGrupoTarea = IdGrupoTarea;
    }

    public Date getFechaTarea() {
        return fechaTarea;
    }

    public void setFechaTarea(Date fechaTarea) {
        this.fechaTarea = fechaTarea;
    }

    public int getIdMiembroTarea() {
        return idMiembroTarea;
    }

    public void setIdMiembroTarea(int idMiembroTarea) {
        this.idMiembroTarea = idMiembroTarea;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }
}
