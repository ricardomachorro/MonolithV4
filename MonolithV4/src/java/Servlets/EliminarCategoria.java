
package Servlets;

import BaseDatos.Database2;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "EliminarCategoria", urlPatterns = {"/EliminarCategoria"})
public class EliminarCategoria extends HttpServlet {
    
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
            HttpSession s = request.getSession();
            String Usuario = s.getAttribute("usuario").toString();
            String Categoria=request.getParameter("NombreCategoria");
            Database2 db=new Database2();
            db.EliminarCategoria(Usuario, Categoria);
            
           

        } catch (Exception ex) {
              System.out.println(ex.toString());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
