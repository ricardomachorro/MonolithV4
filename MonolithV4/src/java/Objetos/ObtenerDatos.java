package Objetos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alex
 */
public class ObtenerDatos {

    String driver = "com.mysql.jdbc.Driver";
    String ruta = "jdbc:mysql://localhost/MonolithV2";
    String usuario = "root";
    String clave = "n0m3l0";

    Connection c = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    int IDUsuario;
    String NombreUsuario;
    String Correo;
    int Edad;
    String Pais;
    String Direccion;
    String Contrasena;
    int TipoUsuario;
    String Validado;
    int Puntos;

    public ObtenerDatos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(driver).newInstance();
        this.c = DriverManager.getConnection(this.ruta, this.usuario, this.clave);
        this.st = c.createStatement();
    }

    public int getIDUsuario(String user) {
        try{
        String sql = "select IDUsuario from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            IDUsuario = rs.getInt("IDUsuario");
        }
        }catch(SQLException e){
            
        }
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getNombreUsuario(String user) {
        try{
        String sql = "select NombreUsuario from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            NombreUsuario = rs.getString("NombreUsuario");
        }
        }catch(SQLException e){
            
        }
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getCorreo(String user) {
        try{
        String sql = "select correo from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Correo = rs.getString("correo");
        }
        }catch(SQLException e){
            
        }
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getEdad(String user) {
        try{
        String sql = "select Edad from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Edad = rs.getInt("Edad");
        }
        }catch(SQLException e){
            
        }
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getPais(String user) {
        try{
        String sql = "select Pais from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Pais = rs.getString("Pais");
        }
        }catch(SQLException e){
            
        }
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getDireccion(String user) {
        try{
        String sql = "select Direccion from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Direccion = rs.getString("Direccion");
        }
        }catch(SQLException e){
            
        }
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getContrasena(String user) {
        try{
        String sql = "select Contrasena from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Contrasena = rs.getString("Contrasena");
        }
        }catch(SQLException e){
            
        }
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public int getTipoUsuario(String user) {
        try{
        String sql = "select TipoUsuario from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            TipoUsuario = rs.getInt("TipoUsuario");
        }
        }catch(SQLException e){
            
        }
        return TipoUsuario;
    }

    public void setTipoUsuario(int TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }

    public String getValidado(String user) {
        try{
        String sql = "select Validado from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Validado = rs.getString("Validado");
        }
        }catch(SQLException e){
            
        }
        return Validado;
    }

    public void setValidado(String Validado) {
        this.Validado = Validado;
    }

    public int getPuntos(String user) {
        try{
        String sql = "select Puntos from Usuario where NombreUsuario = '"+user+"';";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Puntos = rs.getInt("Puntos");
        }
        }catch(SQLException e){
            
        }
        return Puntos;
    }

    public void setPuntos(int Puntos) {
        this.Puntos = Puntos;
    }
}
