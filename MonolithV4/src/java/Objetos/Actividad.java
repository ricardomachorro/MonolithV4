
package Objetos;

import java.sql.Date;


public class Actividad {
    
    private String Titulo;
    private String  Categoria;
    private Date FechaLimite; 
    private String Estado;
    private String Usuario;
    private int IDActviidad;

    public int getIDActviidad() {
        return IDActviidad;
    }

    public void setIDActviidad(int IDActviidad) {
        this.IDActviidad = IDActviidad;
    }

    
    

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public Date getFechaLimite() {
        return FechaLimite;
    }

    public void setFechaLimite(Date FechaLimite) {
        this.FechaLimite = FechaLimite;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    
    
    
    
}

