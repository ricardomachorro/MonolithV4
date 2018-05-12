
package Servlets;

import BaseDatos.Database2;
import Objetos.Nota;
import Objetos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.jms.Session;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.json.*;

@WebServlet(name = "GuardarNota", urlPatterns = {"/GuardarNota"})
public class GuardarNota extends HttpServlet{
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
            Database2 db=new Database2();
            HttpSession s = request.getSession();
            String Usuario = s.getAttribute("usuario").toString();
            String NotaTitulo = request.getParameter("TitleNota");
            String Contenido = request.getParameter("ContenidoNota");
           Nota note=new Nota();
           note.setUsuario(Usuario);
           note.setTitulo(NotaTitulo);
           note.setContenido(Contenido);
           db.IngresarNota(note);

        } catch (Exception ex) {
            String e=ex.toString();
              System.out.println(ex.toString());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
