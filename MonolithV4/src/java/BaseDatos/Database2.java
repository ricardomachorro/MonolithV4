/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.*;
import Objetos.*;
import javax.servlet.http.HttpSession;

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

    public int IDusu(String u) {
        int IDUsuario = 0;
        try {
            String sql = "select * from Usuario where NombreUsuario=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, u);
            rs = ps.executeQuery();
            if (rs.next()) {
                IDUsuario = Integer.parseInt(rs.getString("IDUsuario"));
            }
        } catch (Exception ex) {

        }

        return IDUsuario;
    }

    public boolean Agregardogo(Logro log, String usuario1) {
        boolean agregado = false;
        int IDusuario = 0;
        int costo1 = 0;
        String sql = "select * from Usuario where NombreUsuario=?";
        String sql2 = "Insert into Logro(IDUsuario,Img,Filtro,Nombre,fecha) values(?,?,?,?,?)";
        String sql3 = "update Usuario set Puntos=? where IDUsuario=?";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, usuario1);
            rs = ps.executeQuery();
            if (rs.next()) {
                IDusuario = Integer.parseInt(rs.getString("IDUsuario"));
                costo1 = Integer.parseInt(rs.getString("Puntos"));
            }
            if (costo1 >= 5) {
                ps = c.prepareStatement(sql2);
                ps.setInt(1, IDusuario);
                ps.setInt(2, log.getImg());
                ps.setInt(3, log.getFil());
                ps.setString(4, "dogo #" + log.getN());
                ps.setString(5, log.getFecha());
                ps.execute();
                int costochido = costo1 - 5;
                ps = c.prepareStatement(sql3);
                ps.setInt(1, costochido);
                ps.setInt(2, IDusuario);
                ps.execute();
                agregado = true;
            }

        } catch (Exception ex) {

        }
        return agregado;
    }

    public boolean Intercambio(Intercambio in) {
        boolean intermemo = false;
        String chido = "";
        int img1 = 0;
        int IDusuarioRe = 0;
        String sql1 = "select * from Logro where IDLogro=?";
        String sql2 = "select * from Usuario where NombreUsuario=?";
        String sql3 = "Insert into Intercambio(IDusuarioDa,IDusuarioRe,UsuarioDa,UsuarioRe,FiltroDa,IDdogoDa,IDdogoRe,ImgdogoDa,dogoDa,dogoRe,Estado,fecha) values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = c.prepareStatement(sql1);
            ps.setInt(1, in.getIdimg());
            rs = ps.executeQuery();
            if (rs.next()) {
                chido = rs.getString("Nombre");
                img1 = Integer.parseInt(rs.getString("Img"));
            }
            ps = c.prepareStatement(sql2);
            ps.setString(1, in.getUsuarioRe());
            rs = ps.executeQuery();
            int aux = 0;
            if (rs.next()) {
                IDusuarioRe = Integer.parseInt(rs.getString("IDUsuario"));
                ps = c.prepareStatement(sql3);
                ps.setInt(1, in.getIDusuarioDa());
                ps.setInt(2, IDusuarioRe);
                ps.setString(3, in.getUsuarioDa());
                ps.setString(4, in.getUsuarioRe());
                ps.setString(5, in.getFiltroDa());
                ps.setInt(6, in.getIdimg());
                ps.setInt(7, aux);
                ps.setInt(8, img1);
                ps.setString(9, chido);
                ps.setInt(10, in.getDogoRe());
                ps.setString(11, in.getEstado());
                ps.setString(12, in.getFecha());
                ps.execute();

                intermemo = true;
            } else {
                intermemo = false;
            }

        } catch (Exception ex) {

        }

        return intermemo;
    }

    public boolean NegarIntercambio(Intercambio in) {
        boolean inter = false;
        try {
            String sql = "update Intercambio set Estado='negado',fecha=? where IDInter =?";
            ps = c.prepareStatement(sql);
            ps.setString(1, in.getFecha());
            ps.setInt(2, in.getIDInter());
            ps.executeUpdate();
            inter = true;
        } catch (Exception ex) {

        }
        return inter;
    }

    public boolean AcepInter(Intercambio in) {
        boolean inter1 = false;
        int IDdodoDa = 0;
        int img1 = 0;
        int img2 = 0;
        int filtro1 = 0;
        int filtro2 = 0;
        String nombre1 = "";
        String nombre2 = "";
        try {
            String sql1 = "update Intercambio set IDdogoRe=? where IDInter=?";
            ps = c.prepareStatement(sql1);
            ps.setInt(1, in.getIDdogoRe());
            ps.setInt(2, in.getIDInter());
            ps.executeUpdate();

            String sql2 = "select * from Intercambio where IDInter=?";
            ps = c.prepareStatement(sql2);
            ps.setInt(1, in.getIDInter());
            rs = ps.executeQuery();
            while (rs.next()) {
                IDdodoDa = Integer.parseInt(rs.getString("IDdogoDa"));
            }

            String sql3 = "select * from Logro where IDLogro=?";
            ps = c.prepareStatement(sql3);
            ps.setInt(1, IDdodoDa);
            rs = ps.executeQuery();
            while (rs.next()) {
                img1 = Integer.parseInt(rs.getString("Img"));
                filtro1 = Integer.parseInt(rs.getString("Filtro"));
                nombre1 = rs.getString("Nombre");
            }

            String sql4 = "select * from Logro where IDLogro=?";
            ps = c.prepareStatement(sql4);
            ps.setInt(1, in.getIDdogoRe());
            rs = ps.executeQuery();
            while (rs.next()) {
                img2 = Integer.parseInt(rs.getString("Img"));
                filtro2 = Integer.parseInt(rs.getString("Filtro"));
                nombre2 = rs.getString("Nombre");
            }
            
            String sql5 ="update Intercambio set Estado='aceptada',fecha=? where IDInter = ?" ;
            ps = c.prepareStatement(sql5);
            ps.setString(1, in.getFecha());
            ps.setInt(2, in.getIDInter());
            ps.executeUpdate();

            String sql6 ="update Logro set Img=?,Filtro=?,Nombre=?,fecha=? where  IDLogro=?";
            ps = c.prepareStatement(sql6);
            ps.setInt(1, img1);
            ps.setInt(2, filtro1);
            ps.setString(3, nombre1);
            ps.setString(4, in.getFecha());
            ps.setInt(5, in.getIDdogoRe());
            ps.executeUpdate();
            
            String sql7 ="update Logro set Img=?,Filtro=?,Nombre=?,fecha=? where  IDLogro=?";
            ps = c.prepareStatement(sql7);
            ps.setInt(1, img2);
            ps.setInt(2, filtro2);
            ps.setString(3, nombre2);
            ps.setString(4, in.getFecha());
            ps.setInt(5, IDdodoDa);
            ps.executeUpdate();
            
            inter1 = true;
        } catch (Exception ex) {

        }
        return inter1;
    }

}
