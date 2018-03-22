/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.*;
import Objetos.*;

public class Database2 {

    String driver = "com.mysql.jdbc.Driver";
    String ruta = "jdbc:mysql://localhost/MonolithV2";
    String usuario = "root";
    String clave = "n0m3l0";
    Connection c = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Database2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(driver).newInstance();
        c = DriverManager.getConnection(ruta, usuario, clave);
        st = c.createStatement();

    }

    public void IngresoActividad(Actividad act) {

        try {
            String sqlactv1 = "insert into Categoria(NombreCategoria,IDUsuario) values(?,?)";
            String sqlactv2 = "insert into Actividad(Nombre,IDCategoria,Estado) values(?,?,?)";
            if (CategoriaRepetida(act.getCategoria(), IdentificarUsuario(act.getUsuario()))) {
                String sqlActRepetida = "select * from Categoria where NombreCategoria=? and IDUsaurio=?";
                ps = c.prepareStatement(sqlActRepetida);
                ps.setString(1, act.getCategoria());
                ps.setInt(2, IdentificarUsuario(act.getUsuario()));
                rs = ps.executeQuery();
                int IDCategoria = rs.getInt("IDCategoria");
                ps = c.prepareStatement(sqlactv2);
                ps.setString(1, act.getTitulo());
                ps.setInt(2, IDCategoria);
                ps.setBoolean(3, false);
                ps.execute();

            } else {
                ps = c.prepareStatement(sqlactv1);
                ps.setString(1, act.getCategoria());
                ps.setInt(2, IdentificarUsuario(act.getUsuario()));
                ps.execute();
                String sqlActRepetida = "select * from Categoria where NombreCategoria=? and IDUsaurio=?";
                ps = c.prepareStatement(sqlActRepetida);
                ps.setString(1, act.getCategoria());
                ps.setInt(2, IdentificarUsuario(act.getUsuario()));
                rs = ps.executeQuery();
                int IDCategoria = rs.getInt("IDCategoria");
                ps = c.prepareStatement(sqlactv2);
                ps.setString(1, act.getTitulo());
                ps.setInt(2, 3);
                ps.setBoolean(3, false);
                ps.execute();
            }

        } catch (Exception ex) {
            System.out.println(ex.toString() + "Error de Database2");
        }
    }

    public boolean IngresoUsuario(Usuario user) {
        boolean RegistroExitoso = false;
        String sql1 = "select * from Usuario where NombreUsuario=?";
        String sql2 = "insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,Puntos) values(?,?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(sql1);
            ps.setString(1, user.getNombre());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = c.prepareStatement(sql2);
                ps.setString(1, user.getNombre());
                ps.setString(2, user.getCorreo());
                ps.setInt(3, user.getEdad());
                ps.setString(4, user.getPais());
                ps.setString(5, user.getDireccion());
                ps.setString(6, user.getPassword());
                ps.setInt(7, 0);
                ps.execute();
                RegistroExitoso = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString() + "Error de Database2");
        }
        return RegistroExitoso;
    }

    public boolean IngresoPrograma(Usuario u) {
        boolean IngresoExitoso = false;
        try {
            String sql = "select * from Usuario where NombreUsuario=? and Contrasena=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                IngresoExitoso = true;
            }
        } catch (Exception ex) {

        }

        return IngresoExitoso;
    }

    public int IdentificarUsuario(String Nombre) {
        int ID = 0;
        try {
            String sql = "Select * from Usuario where NombreUsuario=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, Nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                ID = rs.getInt("IDUsuario");
            }
        } catch (Exception ex) {
            return ID;
        }

        return ID;
    }

    public boolean CategoriaRepetida(String Categoria, int IDUsuario) {
        boolean Repetida = false;
        try {
            String sql = "select * from Categoria where NombreCategoria=? and IDUsaurio=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, Categoria);
            ps.setInt(2, IDUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                Repetida = true;
            }
        } catch (Exception ex) {

        }
        return Repetida;
    }

}
