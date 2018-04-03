
package BaseDatos;

import java.sql.*;
import Objetos.*;

public class DataBase {

    String driver = "com.mysql.jdbc.Driver";
    String ruta = "jdbc:mysql://localhost/MonolithV2";
    String usuario = "root";
    String clave = "n0m3l0";
    Connection c = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public DataBase() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(driver).newInstance();
        c = DriverManager.getConnection(ruta, usuario, clave);
        st = c.createStatement();

    }

    public void IngresarUsuario(Usuario usr) throws SQLException {
        String sql = "insert into Usuario(NombreUsuario,Institucion,NivelEstudio,Direccion,Correo, Edad, Pais, Contrasena,Autenticado,Puntos) values (?,?,?,?,?,?,?,?,?,?);";
        String sql2= "";
        /*
        ps = c.prepareStatement(sql);
        ps.setString(1, usr.getNombre());
        ps.setString(2, usr.getInstitucion());
        ps.setString(3, usr.getNivelEstudios());
        ps.setString(4, usr.getDireccion());
        ps.setString(5, usr.getCorreo());
        ps.setInt(6, usr.getEdad());
        ps.setString(7, usr.getPais());
        ps.setString(8, usr.getPassword());
        ps.setString(9, "No");
        ps.setInt(10,0);
        ps.execute();
        c.close();*/
    }
    
    public boolean ConfirmarValidacionUsuario(String NombreUsuario) throws SQLException{
        boolean valiation=false;
        String sql="select from Usuario where Nombre=?;";
        String Validacion="";
        rs = st.executeQuery("select * from usuario where NombreUsuario='" +NombreUsuario+ "'");
        if (rs.next()) {
            Validacion=rs.getString("Autenticado");
            if(Validacion.equalsIgnoreCase("Si")){
                valiation=true;
            }
        }
       return valiation; 
    }

    public void CambiarUsuario(Usuario user, String usuariopeticion) throws SQLException {
        String sql = "update Usuario set NombreUsuario=?,Institucion=?,NivelEstudio=?,Direccion=?,Correo=?,Edad=?,Pais=?,Contrasena=? where Nombreusuario='" + usuariopeticion + "';";
       /* ps = c.prepareStatement(sql);
        ps.setString(1, user.getNombre());
        ps.setString(2, user.getInstitucion());
        ps.setString(3, user.getNivelEstudios());
        ps.setString(4, user.getDireccion());
        ps.setString(5, user.getCorreo());
        ps.setInt(6, user.getEdad());
        ps.setString(7, user.getPais());
        ps.setString(8, user.getPassword());
        ps.execute();
        c.close();*/
    }
      
    public boolean IngresarActividad(Actividad act) throws SQLException {
        boolean exito=true;
        String sql1 = "select * from Categoria where NombreCategoria=?";
        String sql2 = "insert into Actividad(Titulo,IDUsuario,Estado,IDCategoria) values (?,?,?,?);";
        int idUsuario= ConsultarUsuario(act.getUsuario());
        if(idUsuario!=0){
             ps=c.prepareStatement(sql1);
             ps.setString(1,act.getCategoria());
             ResultSet rs1=ps.executeQuery();
             if(rs1.next()){
                 int IDCategoria=rs1.getInt("IDCategoria");
                 ps=c.prepareStatement(sql2);
                 ps.setString(1,act.getTitulo());
                 ps.setInt(2,idUsuario);
                 ps.setBoolean(3,false);
                 ps.setInt(4, IDCategoria);
                 
             }else{
                 String sql3="insert into Categoria(Nombre,IDUsario) values(?,?)";
                 ps=c.prepareStatement(sql3);
                 ps.setString(1,act.getCategoria());
                 ps.setInt(2,idUsuario);
                 int IDCategoria=rs1.getInt("IDCategoria");
                 ps=c.prepareStatement(sql2);
                 ps.setString(1,act.getTitulo());
                 ps.setInt(2,idUsuario);
                 ps.setBoolean(3,false);
                 ps.setInt(4, IDCategoria);
             }
        }
        ps.execute();
        c.close();
        return exito;
    }

    /*
    
    public void IngresarActividad(Actividad act) throws SQLException {
        String sql = "insert into actividad(Titulo,FormaDeEntregar,Descripcion,IDUsuario,FechaLimite,Estado) values (?,?,?,?,?,?);";
        ps = c.prepareStatement(sql);
        ps.setString(1, act.getTitulo());
        ps.setString(2, act.getFormadeEntregar());
        ps.setString(3, act.getDescripcion());
        ps.setInt(4, ConsultarUsuario(act.getUsuario()));
        ps.setDate(5, act.getFechaLimite());
        ps.setString(6, "No finalizada");
        ps.execute();
        c.close();
    }

    public void CambiarActividad(Actividad act, String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "update actividad set Titulo=?,FormaDeEntregar=?,FechaLimite=? ,Descripcion = ? where IDActividad=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.setString(1, act.getTitulo());
        ps.setString(2, act.getFormadeEntregar());
        ps.setDate(3, act.getFechaLimite());
        ps.setString(4, act.getDescripcion());
        ps.execute();
        c.close();
    }

    public void EliminarActividad(String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "delete from actividad where IDActividad=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.execute();
        c.close();
    }
    
    public void CambiarActividadFinalizada(String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "update actividad set Estado='Finalizada' where IDActividad=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.execute();
        c.close();
    }
    
     public void CambiarActividadNoFinalizada(String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "update actividad set Estado='No Finalizada' where IDActividad=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.execute();
        c.close();
    }*/
     
     public void CambiarExamenFinalizado(String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "update examen set Estado='Finalizada' where IDExamen=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.execute();
        c.close();
    }
    
     public void CambiarExamenNoFinalizado(String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "update examen set Estado='No Finalizada' where IDExamen=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.execute();
        c.close();
    }

     /*
    public void IngresarExamen(Examen exa) throws SQLException {
        String sql = "insert into examen(Titulo,FormaDeEntregar,Descripcion,IDUsuario,FechaLimite,Estado) values (?,?,?,?,?,?);";
        ps = c.prepareStatement(sql);
        ps.setString(1, exa.getTitulo());
        ps.setString(2, exa.getFormadeEntregar());
        ps.setString(3, exa.getDescripcion());
        ps.setInt(4, ConsultarUsuario(exa.getUsuario()));
        ps.setDate(5, exa.getFechaLimite());
        ps.setString(6, "No finalizada");
        ps.execute();
        c.close();
    }
    

    public void CambiarExamen(Examen ex, String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "update examen set Titulo=?,FormaDeEntregar=?,FechaLimite=? ,Descripcion = ? where IDExamen=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.setString(1, ex.getTitulo());
        ps.setString(2, ex.getFormadeEntregar());
        ps.setDate(3, ex.getFechaLimite());
        ps.setString(4, ex.getDescripcion());
        ps.execute();
        c.close();
    }*/

    public void EliminarExamen(String id) throws SQLException {
        int identic = Integer.parseInt(id);
        String sql = "delete from examen where IDExamen=" + identic + ";";
        ps = c.prepareStatement(sql);
        ps.execute();
        c.close();
    }

    public int ConsultarUsuario(String usr) throws SQLException {
        int num = 0;
        rs = st.executeQuery("select * from usuario where NombreUsuario='" + usr + "'");
        if (rs.next()) {
            num = rs.getInt("IDUsuario");
        }
        return num;
    }

    public int ConsultarColaborador(String usr) throws SQLException {
        int num = 0;
        rs = st.executeQuery("select * from colaboradores inner join usuario on usuario.IDUsuario=colaboradores.IDUsuario where usuario.NombreUsuario='" + usr + "'");
        if (rs.next()) {
            num = rs.getInt("IDUsuario");
        }
        return num;
    }

}
