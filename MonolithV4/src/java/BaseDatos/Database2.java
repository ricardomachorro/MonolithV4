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
    
    //Datos para la conexion
    String driver = "com.mysql.jdbc.Driver";
    String ruta = "jdbc:mysql://localhost/MonolithV2";
    String usuario = "root";
    String clave = "n0m3l0";
    
    Connection c = null;
    Statement st = null;
    PreparedStatement ps = null; //Secuencia facil de ejecutar xd
    ResultSet rs = null;

    public Database2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //Conexion a la BD
        Class.forName(driver).newInstance();
        this.c = DriverManager.getConnection(this.ruta, this.usuario, this.clave);
        //Metodo para ejecutar secuencias sql
        this.st = c.createStatement();
    }
    
    public void IngresarNota(Nota note) throws Exception{//NOtas
        if(!NotaExistente(note)){
         int IDUsuario=IdentificarUsuario(note.getUsuario());
        String sql="insert into Nota (Nombre,Contenido,IDUsuario) values('"+note.getTitulo()+"','"+note.getContenido()+"',"+IDUsuario+")";
        st=c.createStatement();
        st.executeUpdate(sql);
        }
    }
    
    public void ActualizarNota(Nota note, String NombreAnterior)throws Exception{ //Notas
        int IDUsuario=IdentificarUsuario(note.getUsuario());
        String sql="update Nota set Nombre='"+note.getTitulo()+"' set Contenido='"+note.getContenido()+"' where Nombre='"+NombreAnterior+"' and IDUauario="+IDUsuario;
         st=c.createStatement();
        st.executeUpdate(sql);
    }
    
    private boolean NotaExistente(Nota note) throws Exception{//Notas
        boolean existe=false;
        String sql="Select * from Nota inner join Usuario on Nota.IDUsuario=Usuario.IDUsuario where Usuario.NombreUsuario='"+note.getUsuario()+"' and Nota.Nombre='"+note.getTitulo()+"'";
        st=c.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()){
            existe=true;
        }
        
        return existe;
    }
    
    public String NotaContenido(String Usuario, String Nota) throws Exception{//Notas
        String Contenido="";
        String sql="Select * from Nota inner join Usuario on Nota.IDUsuario=Usuario.IDUsuario where Usuario.NombreUsuario='"+Usuario+"' and Nota.Nombre='"+Nota+"'";
        st=c.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()){
          Contenido=rs.getString("Contenido");
        }
        return Contenido;
    }

    public int IngresoActividad(Actividad act) {//Actividades
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

    public boolean ActualizacionActividad(int IDActividad,String Nombre,String Categoria,String Fecha,String Usuario){//Actividades
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
            System.out.println("Error: " + ex.getMessage());
        }
        return ActActualizada;
    }
    
    private void AgregarPuntos(String Usuario) throws Exception{//Logros
        int Puntos=PuntosUsuario(Usuario) +1;
        String sql="update Usuario set Puntos="+Puntos+" where NombreUsuario='"+Usuario+"'";
          st=c.createStatement();
          st.executeUpdate(sql);
    }
    
    private void Eliminarpuntos(String Usuario) throws Exception{//Logros
        int Puntos=PuntosUsuario(Usuario);
        if( Puntos>0){
            Puntos=Puntos-1;
           String sql="update Usuario set Puntos="+Puntos+" where NombreUsuario='"+Usuario+"'";
          st=c.createStatement();
          st=c.createStatement();
          st.executeUpdate(sql);
        }
        
    }
    
    private int PuntosUsuario(String Usuario) throws Exception{//Logros
        int puntos=-1;
         String sql="Select * from Usuario where NombreUsuario='"+Usuario+"'";
          st=c.createStatement();
          rs=st.executeQuery(sql);
          while(rs.next()){
              puntos=rs.getInt("Puntos");
          }
        return puntos;
    }
    
    
   private boolean ActividadEstado(int IDActividad) {//Actividades
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

    public void EliminarActividad(int IDActividad){//Actividades
        try {
            String sql1 = "delete from Actividad where IDActividad=?";
            ps = c.prepareStatement(sql1);
            ps.setInt(1,IDActividad);
            ps.execute();
           
        } catch (Exception ex) {

        }
    }
    
    public void EliminarCategoria( String Usuario, String Categoria){//Actividades
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

    public boolean IngresoUsuario(Usuario user) {//Index
        boolean RegistroExitoso = false;
      
        String sql2 = "insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,Puntos,TipoUsuario,Validado) values(?,?,?,?,?,?,?,?,?)";
        try {
            if(!UsuarioRepetido(user) && !CorreoRepetido(user)) {//Evalua que no halla otro usuario registrado con el mismo nombre
                
                ps = c.prepareStatement(sql2);
                
                ps.setString(1, user.getNombre());
                ps.setString(2, user.getCorreo());
                ps.setInt(3, user.getEdad());
                ps.setString(4, user.getPais());
                ps.setString(5, user.getDireccion());
                ps.setString(6, user.getPassword());
                ps.setInt(7, 0);
                ps.setInt(8,1);
                ps.setString(9,"No");
                //Ejecuta
                ps.executeUpdate();
                
                String ParametrosHash = user.getNombre() + user.getPassword();
                int HashCode = ParametrosHash.hashCode();
                int adendum;
                if (HashCode < 0) {
                    adendum = HashCode * -1;
                } else {
                    adendum = HashCode;
                }
                
                RegistrarValidacion(user.getNombre(),adendum);
                
                RegistroExitoso = true; 
 
            }
        } catch (Exception ex) {
            System.out.println(ex.toString() + "Error de Database2");
        }
        return RegistroExitoso;
    }
    
    public boolean RegistrarValidacion(String Usuario,int Adendum)throws Exception{
        boolean RegistroValidacion=false;
        String sql3="insert into Validacion (Nombre,adendum) values (?,?)";
         ps = c.prepareStatement(sql3);
         ps.setString(1, Usuario);
         ps.setInt(2,Adendum);
         ps.executeUpdate();
         RegistroValidacion=true;
        return RegistroValidacion;
    }
    
    public String ResultadoFinalValidacion(String NombreUsuario) throws Exception {
        String Resultado="";
         String sql="select * from Validacion where Nombre=?";
         ps = c.prepareStatement(sql);
         ps.setString(1, NombreUsuario);
         rs=ps.executeQuery();
         if(rs.next()){
           Resultado=rs.getString("adendum");
         }
        
        
        return Resultado;
    }
    
    public void DarValidoUsuario(String Usuario) throws Exception{
        String sql1="update Usuario set Validado='Si' where NombreUsuario=?";
        ps=c.prepareStatement(sql1);
        ps.setString(1,Usuario);
        ps.executeUpdate();
        String sql2="delete from Validacion where Nombre=?";
        ps=c.prepareStatement(sql2);
        ps.setString(1,Usuario);
        ps.executeUpdate();
    }
    public boolean UsuarioRepetido(Usuario user) throws Exception{
        boolean Repetido=false;
         String sql1 = "select * from Usuario where NombreUsuario=?";
          ps = c.prepareStatement(sql1);
            ps.setString(1, user.getNombre());
            rs = ps.executeQuery();
           Repetido = rs.next();
        return Repetido;
    }
    
    public boolean CorreoRepetido(Usuario user) throws Exception{
        boolean Repetido=false;
         String sql1 = "select * from Usuario where Correo=?";
          ps = c.prepareStatement(sql1);
            ps.setString(1, user.getCorreo());
            rs = ps.executeQuery();
           Repetido = rs.next();
        return Repetido;
    }

    public boolean IngresoPrograma(Usuario u) {//Registro
        boolean IngresoExitoso = false;
        try {
            String sql = "select * from Usuario where NombreUsuario=? and Contrasena=?";
            ps = c.prepareStatement(sql); //Inserta secuencia
            ps.setString(1, u.getNombre()); //Primer signo de interrogacion
            ps.setString(2, u.getPassword()); //Segundo signo de interrogacion
            rs = ps.executeQuery(); //Se hace query
            if (rs.next()) {//Evalua si se hizo query
                IngresoExitoso = true;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return IngresoExitoso;
    }

    public int IdentificarUsuario(String Nombre) {//Cualquiera
        int ID = 0;
        String sql = "Select IDUsuario from Usuario where NombreUsuario=?";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, Nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                ID = rs.getInt("IDUsuario");
            }
            rs.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return ID;
        }

        return ID;
    }

    public boolean CategoriaExistente(String Categoria, int IDUsuario) {//Actividades
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
    
    public void ActualizarUsuario(Usuario user,String UsuarioOriginal){//Configuracion
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
    
    public int ContadorActividadesFaltantes(String NombreUsuario){//Actividades
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
    
    public int ContadorActividadesFinalizadas(String NombreUsuario){//Actividades
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
    
    
     public boolean CambiarEstadoActividad(int IDActividad,String Usuario) {//Actividades
        boolean ActividadCambiadaExitosa = false;
        boolean Estado = false;
        try {
            Estado=ActividadEstado(IDActividad);
            String sql="";
            if(Estado){
                 sql = "update Actividad set Estado=false where IDActividad="+ IDActividad+"";
                 Eliminarpuntos(Usuario);
                 
            }else{
                  sql = "update Actividad set Estado= true where IDActividad="+ IDActividad+"";
                  AgregarPuntos(Usuario);
            }
            
            st = c.createStatement();
            st.executeUpdate(sql);
            
        } catch (Exception ex) {

        }

        return ActividadCambiadaExitosa;
    }
     
     /*Inicio de los metodos para grupos*/
    public String consultarMiembro(String correoMiembro) throws Exception{
        String nombreMiembro = "";
        try {
            //Query a ejecutar
            String sqlBuscarMiembro = "select NombreUsuario from Usuario where Correo='"+correoMiembro+"';";
            //Ejecuto query
            rs = st.executeQuery(sqlBuscarMiembro);
            if(rs.next()) { //Si sale algo
                nombreMiembro = rs.getString("NombreUsuario"); //Asigno el nombre de usuario
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return nombreMiembro;//Regreso el nombre de usuario
    }
    
    public void crearGrupo(Grupo g) throws Exception{
        String nombreGrupo = g.getNombreGrupo();
        int IDLider = g.getIDLider();
        int IDMiembros[] = g.getMiembros();
        int IDGrupo;
        int idMiembro;
        
        String queryNG = "into Grupo(NombreGrupo) values('"+nombreGrupo+"');";
        String queryLider;
        String queryMiembro;
        try {
            st = c.createStatement();
            st.execute(queryNG);//creo el grupo
            IDGrupo = IDGrupo(nombreGrupo);//Traigo su id
            
            queryLider = "insert into Miembros(IDUsuario,IDGrupo,IDRol) values("+IDLider+","+IDGrupo+",1);";
            st.execute(queryLider);//Le agrego al lider
            
            if(IDMiembros.length!=0){//Agrego miembros sies que hay :v
                for(int i=0; i<IDMiembros.length; i++) {
                    idMiembro = IDMiembros[i];
                    queryMiembro = "insert into Miembros(IDUsuario,IDGrupo,IDRol) values("+idMiembro+","+IDGrupo+",2);";
                    st.execute(queryMiembro);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public int IDGrupo(String nombreGrupo) throws Exception{
        int id = 0;
        String query = "select IDGrupo from Grupo where NombreGrupo='"+nombreGrupo+"';";
        try {
            st = c.createStatement();
            rs = st.executeQuery(query);
            if(rs.next()){
                id = rs.getInt("IDGrupo");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return id;
    }
    /*Fin metodos para grupos*/
}