package Servlets;

import BaseDatos.Database2;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "CrearGrupo", urlPatterns = {"/CrearGrupo"})
public class CrearGrupo extends HttpServlet {

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
            //Traigo los parametros de la data de ajax
            String nombreGrupo = request.getParameter("nomNuevoGrupo");
            String nombreLider = request.getParameter("lider");
            //String[] miembros = request.getParameter("miembros");
                System.out.println(nombreLider);
            
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
            String nombreLider = "fallo";
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
