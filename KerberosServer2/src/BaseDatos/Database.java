/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    
    String driver = "com.mysql.jdbc.Driver";
    String ruta = "jdbc:mysql://localhost/KerberosReal";
    String usuario = "root";
    String clave = "n0m3l0";
    Connection c = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Database() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(driver).newInstance();
        c = DriverManager.getConnection(ruta, usuario, clave);
        st = c.createStatement();

    }
    
    public boolean IdentificarUsuario(String Nombre,int IDentificador) {
        boolean exito=false;
        try {
            String sql = "Select * from UsuarioKerberos where NombreUsuarioKerberos=?,Identificador=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, Nombre);
            ps.setInt(2, IDentificador);
            rs = ps.executeQuery();
            if (rs.next()) {
                exito=true;
            }
        } catch (Exception ex) {
            return exito;
        }

        return exito;
    }
    
}
