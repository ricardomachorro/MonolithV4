package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BaseDatos.Database2;
import Objetos.Grupo;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "CrearGrupos", urlPatterns = {"/CrearGrupos"})
public class CrearGrupos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Inicializo el objeto grupo
            Grupo g = new Grupo();
            //Traigo los parametros de la data de ajax
            String nombreGrupo = request.getParameter("nomNuevoGrupo");
            String nombreLider = request.getParameter("lider");
            String stringMiembros = request.getParameter("miembros");
            
            /*Parametros que usare para ingresar datos a la db desde el objeto*/
            //Array de strings a array de int's
            String[] miembros = stringMiembros.split(",");
            int[] IDMiembros = new int[miembros.length];
            int i = 0;
            for(String miembro:miembros)
            {
                IDMiembros[i] = db.IdentificarUsuario(miembro);//Las id's se traen la la bd
                i++;
            }
            int IDLider = db.IdentificarUsuario(nombreLider);//Obtengo la id del lider
            //Meto los datos a mi objeto
            g.setIDLider(IDLider);
            g.setNombreGrupo(nombreGrupo);
            g.setMiembros(IDMiembros);
            //Agrego el grupo a la bd
            db.crearGrupo(g);
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
