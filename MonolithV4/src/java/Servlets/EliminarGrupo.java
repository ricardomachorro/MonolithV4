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
@WebServlet(name = "EliminarGrupo", urlPatterns = {"/EliminarGrupo"})
public class EliminarGrupo extends HttpServlet {

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
            //Traigo el parametro idGrupo de la data de ajax
            String idGrupo = request.getParameter("Grupo");
            //Combierto la id a int
            int id = Integer.parseInt(idGrupo);
            //Borro el grupo
            db.eliminarGrupo(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " :'v");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
