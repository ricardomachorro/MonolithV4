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
        try {
            //Conexion a la BD
            Class.forName(driver).newInstance();
            this.c = DriverManager.getConnection(this.ruta, this.usuario, this.clave);
            //Metodo para ejecutar secuencias sql
            this.st = (Statement) c.createStatement();
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage() + " :'v");
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

            if (!rs.next()) {//Evalua que no halla otro usuario registrado con el mismo nombre
                //Inicia
                ps = c.prepareStatement(sql2); 
                //Llena
                ps.setString(1, user.getNombre());
                ps.setString(2, user.getCorreo());
                ps.setInt(3, user.getEdad());
                ps.setString(4, user.getPais());
                ps.setString(5, user.getDireccion());
                ps.setString(6, user.getPassword());
                ps.setInt(7, 0);
                //Ejecuta
                ps.execute();

                RegistroExitoso = true; //Registro exitoso
            }
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex.toString() + "Error de Database2");
        }
        return RegistroExitoso;
    }

    public boolean IngresoPrograma(Usuario u) {
        boolean IngresoExitoso = false;
        try {
            String sql = "select * from Usuario where NombreUsuario=? and Contrasena=?"; //Busca usuario y contraseña
            ps = c.prepareStatement(sql); //Inserta secuencia
            ps.setString(1, u.getNombre()); //Primer signo de interrogacion
            ps.setString(2, u.getPassword()); //Segundo signo de interrogacion
            rs = ps.executeQuery(); //Se hace query

            if (rs.next()) { //Evalua si se hizo query
                IngresoExitoso = true;
            }
            rs.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage() + " :'v");
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
        
        String queryNG = "insert into Grupo(NombreGrupo) values('"+nombreGrupo+"');";
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
    
    public void crearTarea(Tarea a) throws Exception{
        //Asigno los datos alv
        String Nombre = a.getNombreTarea();
        int IDGrupo = a.getIdGrupoTarea();
        Date Fecha = a.getFechaTarea();
        int IDMiembro = a.getIdMiembroTarea();
        int idTarea = 1;
        
        String queryCrearTarea = "insert into Tarea(Nombre,IDGrupo,Fecha,Estado) values('"+Nombre+"', "+IDGrupo+",'"+Fecha+"', false);";
        String queryIDTarea = "select IDTarea from Tarea where IDTarea=(select max(IDTarea) from Tarea);";
        try {
            st = c.createStatement();
            st.execute(queryCrearTarea);//Creo la nueva tarea
            //Traigo la id de esa tarea para asignarla
            rs = st.executeQuery(queryIDTarea);
            while(rs.next()) {
                idTarea = rs.getInt("IDTarea");
            }
            asignarMiembro(idTarea,IDMiembro);
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString() + " - Error de Database2");
        }
    }
    
    public void asignarMiembro(int IDTarea, int IDMiembro) throws Exception {
        try{
            String queryAsigMiembro = "insert into TareaMiembro(IDTarea,IDMiembro) values ("+IDTarea+","+IDMiembro+");";
            st = c.createStatement();
            st.execute(queryAsigMiembro);
        } catch (Exception e){
            System.out.println("Error agregando miembro: " + e.toString());
        }
    }
    
    public int traerIDTarea() throws Exception {
        int idTarea = 0;
        String queryIDTarea = "select IDTarea from Tarea where IDTarea=(select max(IDTarea) from Tarea);";
        try {
            st = c.createStatement();
            rs = st.executeQuery(queryIDTarea);
            while(rs.next()) {
                idTarea = rs.getInt("IDTarea");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString() + " - Error");
        }
        return idTarea;
    }
    
    public void eliminarTarea(int idTarea) throws Exception {
        String queryEliminar = "delete from Tarea where IDTarea=?;";
        try {
            ps = c.prepareStatement(queryEliminar);
            ps.setInt(1, idTarea);
            ps.execute();
        } catch (Exception e) {
            System.out.println(e.toString() + " - Error");
        }
    }
    /*Fin de los metodos para grupos*/
}
