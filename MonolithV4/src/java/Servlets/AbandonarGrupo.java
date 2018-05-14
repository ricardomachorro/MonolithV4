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
@WebServlet(name = "AbandonarGrupo", urlPatterns = {"/AbandonarGrupo"})
public class AbandonarGrupo extends HttpServlet {

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
            String idGrupo = request.getParameter("Grupo");
            String idUsuario = request.getParameter("Usuario");
            //Combierto las id's a int
            int id = Integer.parseInt(idGrupo);
            int usu = Integer.parseInt(idUsuario);
            //Borro al miembro
            db.abandonarGrupo(id, usu);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + " :'v");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
