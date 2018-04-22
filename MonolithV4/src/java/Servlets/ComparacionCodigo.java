
package Servlets;


import java.io.*;
import java.net.InetAddress;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import Seguridad.Cifrados;

@WebServlet(name = "ComparacionCodigo", urlPatterns = {"/ComparacionCodigo"})
public class ComparacionCodigo extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Ingreso</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Ingreso at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        String nombre  = request.getParameter("NomUsuarioCom");
        String  Codigo= request.getParameter("CodigoCom");
        String  Contra=request.getParameter("ContraCom");
        Cifrados sec = new Cifrados();
        sec.addKey(Contra);
        String texto = sec.encriptar(Codigo);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
