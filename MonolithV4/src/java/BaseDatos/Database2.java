/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.*;
import Objetos.*;
import java.util.Date;
import java.text.SimpleDateFormat;

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

    public int IngresoActividad(Actividad act) {
        int IDActividad=0;
        try {
            
            int IDCategoria = 0;
            if (CategoriaExistente(act.getCategoria(), IdentificarUsuario(act.getUsuario()))) {
                String sqlBusquedaCategoria = "select * from Categoria where IDUsuario=" + IdentificarUsuario(act.getUsuario()) + " and NombreCategoria='" + act.getCategoria() + "' ";
                rs = st.executeQuery(sqlBusquedaCategoria);
                while (rs.next()) {
                    IDCategoria = rs.getInt("IDCategoria");
                }
                String sqlInsertarActividad = "insert into Actividad(Nombre,IDCategoria,Fecha,Estado) values('" + act.getTitulo() + "'," + IDCategoria + ",CURDATE(),false)";
                st.execute(sqlInsertarActividad);

            } else {
                String sqlEntradaCategoria = "insert into Categoria (NombreCategoria , IDUsuario) values ('" + act.getCategoria() + "'," + IdentificarUsuario(act.getUsuario()) + ")";
                String sqlBusquedaCategoria = "select * from Categoria where IDUsuario=" + IdentificarUsuario(act.getUsuario()) + " and NombreCategoria='" + act.getCategoria() + "' ";
                st = c.createStatement();
                st.execute(sqlEntradaCategoria);
                rs = st.executeQuery(sqlBusquedaCategoria);
                while (rs.next()) {
                    IDCategoria = rs.getInt("IDCategoria");
                }
                String sqlInsertarActividad = "insert into Actividad(Nombre,IDCategoria,Fecha,Estado) values('" + act.getTitulo() + "'," + IDCategoria + ",CURDATE(),false)";
                st.execute(sqlInsertarActividad);
                String sqlActividadID="select * from Actividad where IDActividad=(select max(IDActividad) from Actividad)";
                rs=st.executeQuery(sqlActividadID);
                while (rs.next()) {
                    IDActividad = rs.getInt("IDActividad");
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return  IDActividad;
    }

    public boolean ActualizacionActividad(int IDActividad,String Nombre,String Categoria,String Fecha,String Usuario){
        boolean ActActualizada=false;
        try{
            if(CategoriaExistente(Categoria, IdentificarUsuario(Usuario))){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               String dateInString = Fecha;
                Date fechautil= formatter.parse(dateInString);
                String sqlUpdateActividad="update Actividad set Nombre=?,Fecha=? where IDActividad=?";
                ps=c.prepareStatement(sqlUpdateActividad);
                ps.setString(1, Nombre);
                  Timestamp timestamp = new Timestamp(fechautil.getTime());
                ps.setTimestamp(2,timestamp);
                ps.setInt(3,IDActividad);
                ps.execute();
            }else{
                int IDCategoria=0;
                String sqlEntradaCategoria = "insert into Categoria (NombreCategoria , IDUsuario) values ('" + Categoria + "'," + IdentificarUsuario(Usuario) + ")";
                String sqlBusquedaCategoria = "select * from Categoria where IDUsuario=" + IdentificarUsuario(Usuario) + " and NombreCategoria='" + Categoria + "' ";
                st = c.createStatement();
                st.execute(sqlEntradaCategoria);
                rs = st.executeQuery(sqlBusquedaCategoria);
                while (rs.next()) {
                    IDCategoria = rs.getInt("IDCategoria");
                }
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               String dateInString = Fecha;
                Date fechautil= formatter.parse(dateInString);
                
                String sqlUpdateActividad="update Actividad set Nombre=?,IDCategoria=?,Fecha=? where IDActividad=?";
                ps=c.prepareStatement(sqlUpdateActividad);
                ps.setString(1, Nombre);
                ps.setInt(2,IDCategoria);
               Timestamp timestamp = new Timestamp(fechautil.getTime());
                ps.setTimestamp(3,timestamp);
                ps.setInt(4, IDActividad);
                ps.execute();
            }
            
        }catch(Exception ex){
            
        }
        return ActActualizada;
    }
    
    public boolean CambiarEstadoActividad(int IDActividad) {
        boolean ActividadCambiadaExitosa = false;
        boolean Estado = false;
        try {
            Estado=ActividadEstado(IDActividad);
            String sql="";
            if(Estado){
                 sql = "update Actividad set Estado=false where IDActividad="+ IDActividad+"";
            }else{
                  sql = "update Actividad set Estado= true where IDActividad="+ IDActividad+"";
            }
            
            st = c.createStatement();
            st.executeUpdate(sql);
            
        } catch (Exception ex) {

        }

        return ActividadCambiadaExitosa;
    }

    public void EliminarActividad(int IDActividad){
        try {
            String sql1 = "delete from Actividad where IDActividad=?";
            ps = c.prepareStatement(sql1);
            ps.setInt(1,IDActividad);
            ps.execute();
           
        } catch (Exception ex) {

        }
    }
    
    public void EliminarCategoria( String Usuario, String Categoria){
        try{
            
            int IDUsuario=IdentificarUsuario(Usuario);    
            String sql1 = "delete from Categoria where IDUsuario=? and NombreCategoria=?";
          ps = c.prepareStatement(sql1);
            ps.setInt(1,IDUsuario);
            ps.setString(2,Categoria);
            ps.execute();
        }catch(Exception ex){
            
        }
    }
    
    private boolean ActividadEstado(int IDActividad) {
        boolean EstadoActividad = false;
        
        try {
            String sql1 = "Select * from Actividad where IDActividad=?";
            ps = c.prepareStatement(sql1);
            ps.setInt(1,IDActividad);
            rs = ps.executeQuery();
            if(rs.next()) {
                EstadoActividad = rs.getBoolean("Estado");
                
            }
        } catch (Exception ex) {

        }

        return EstadoActividad;
    }
    
    public int TipoUsuario(Usuario user){
        int tipo=0;
        try {
            String sql = "select * from Usuario where NombreUsuario=? and Contrasena=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                tipo=rs.getInt("TipoUsuario");
            }
        } catch (Exception ex) {

        }
        return tipo;
    }

    public boolean IngresoUsuario(Usuario user) {
        boolean RegistroExitoso = false;
        String sql1 = "select * from Usuario where NombreUsuario=?";
        String sql2 = "insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,Puntos,TipoUsuario) values(?,?,?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(sql1);
            ps.setString(1, user.getNombre());
            rs = ps.executeQuery();
            boolean n=rs.next();
           if(!rs.next()) {
                ps = c.prepareStatement(sql2);
                ps.setString(1, user.getNombre());
                ps.setString(2, user.getCorreo());
                ps.setInt(3, user.getEdad());
                ps.setString(4, user.getPais());
                ps.setString(5, user.getDireccion());
                ps.setString(6, user.getPassword());
                ps.setInt(7, 0);
                ps.setInt(8,1);
                ps.executeUpdate();
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

    public boolean CategoriaExistente(String Categoria, int IDUsuario) {
        boolean Repetida = false;
        try {
            String sql = "select * from Categoria where NombreCategoria=? and IDUsuario=?";
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
    
    public void ActualizarUsuario(Usuario user,String UsuarioOriginal){
        String sql="update Usuario set NombreUsuario=?,Correo=?,Edad=?,Pais=?,Direccion=?,Contrasena=? where IDUsuario=?";
        try{
            int idUsuario=IdentificarUsuario(UsuarioOriginal);
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2,user.getCorreo());
            ps.setInt(3, user.getEdad());
            ps.setString(4, user.getPais());
            ps.setString(5, user.getDireccion());
            ps.setString(6, user.getPassword());
            ps.setInt(7, idUsuario);
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    
    public int ContadorActividadesFaltantes(String NombreUsuario){
        int ActividadesFaltantes=0;
        try{
            String sql="select * from Actividad inner join Categoria on Actividad.IDCategoria=Categoria.IDCategoria inner join Usuario on Categoria.IDUsuario=Usuario.IDUsuario  where Usuario.NombreUsuario=? and Actividad.Estado=0";
            ps = c.prepareStatement(sql);
         ps.setString(1, NombreUsuario);
         rs=ps.executeQuery();
         if(rs.last()){
             ActividadesFaltantes=rs.getRow();
         }
        }catch(Exception ex){
        
    }
        return ActividadesFaltantes;
    }
    
    public int ContadorActividadesFinalizadas(String NombreUsuario){
        int ActividadesFinzalizadas=0;
        try{
            String sql="select * from Actividad inner join Categoria on Actividad.IDCategoria=Categoria.IDCategoria inner join Usuario on Categoria.IDUsuario=Usuario.IDUsuario  where Usuario.NombreUsuario=? and Actividad.Estado>0";
            ps = c.prepareStatement(sql);
         ps.setString(1, NombreUsuario);
         rs=ps.executeQuery();
         if(rs.last()){
             ActividadesFinzalizadas=rs.getRow();
         }
        }catch(Exception ex){
        
    }
        return ActividadesFinzalizadas;
    }

}
