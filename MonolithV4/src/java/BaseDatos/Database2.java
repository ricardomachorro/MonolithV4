/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;
import java.sql.*;
import Objetos.*;
public class Database2 {
    
    //Datos para la conexion
    private String driver = "com.mysql.jdbc.Driver";
    private String ruta = "jdbc:mysql://localhost/MonolithV2";
    private String usuario = "root";
    private String clave = "n0m3l0";
    
    private Connection c = null;
    private Statement st = null;
    private PreparedStatement ps = null; //Secuencia facil de ejecutar
    private ResultSet rs = null;

    public Database2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try{
            //Conexion a la BD
            Class.forName(driver).newInstance();
            this.c = DriverManager.getConnection(this.ruta, this.usuario, this.clave);
            //Metodo para ejecutar secuencias sql
            this.st = (Statement) c.createStatement();
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage() + " :'v");
        }
    }
    
    public boolean IngresoUsuario(Usuario user){
        boolean RegistroExitoso=false;
        String sql1="select * from Usuario where NombreUsuario=?";
         String sql2="insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,Puntos) values(?,?,?,?,?,?,?)";
        try{
             ps=c.prepareStatement(sql1);
             ps.setString(1,user.getNombre());
             rs=ps.executeQuery();
             if(!rs.next()){
                 ps=c.prepareStatement(sql2);
                 ps.setString(1,user.getNombre());
                 ps.setString(2, user.getCorreo());
                 ps.setInt(3, user.getEdad());
                 ps.setString(4, user.getPais());
                 ps.setString(5,user.getDireccion());
                 ps.setString(6,user.getPassword());
                 ps.setInt(7,0);
                 ps.execute();
                 RegistroExitoso=true;
             }
    }catch(Exception ex){
        System.out.println(ex.toString()+"Error de Database2");
    }
        return RegistroExitoso;
    }
    
    public boolean IngresoPrograma(Usuario u){
        boolean IngresoExitoso = false;
        try{
            String sql = "select * from Usuario where NombreUsuario=? and Contrasena=?"; //Busca usuario y contraseña
            ps = c.prepareStatement(sql); //Inserta secuencia
            ps.setString(1,u.getNombre()); //Primer signo de interrogacion
            ps.setString(2,u.getPassword()); //Segundo signo de interrogacion
            rs = ps.executeQuery(); //Se hace query
            
            if(rs.next()){ //Evalua si se hizo query
                IngresoExitoso = true;
            }
        } 
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage() + " :'v");
        }
        return IngresoExitoso;
    }
    
}
