
package Servlets;
import BaseDatos.Database2;
import Objetos.Usuario;
import java.io.*;
import java.net.InetAddress;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ActualizarUsuario", urlPatterns = {"/ActualizarUsuario"})

public class ActualizarUsuario extends HttpServlet{
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
        Usuario u = new Usuario();
        
        try {
            Database2 db = new Database2();
            HttpSession sesion = request.getSession();
           String Usuario = sesion.getAttribute("usuario").toString();
           String Nombre=request.getParameter("Nombre").toString();
           String Edad=request.getParameter("Edad").toString();
           int edad=Integer.parseInt(Edad);
           String Pais=request.getParameter("Pais").toString();
           String Direc=request.getParameter("Direc").toString();
           String Correo=request.getParameter("Correo").toString();
           String Contra=request.getParameter("Contra").toString();
           u.setNombre(Nombre);
           u.setEdad(edad);
           u.setPais(Pais);
           u.setDireccion(Direc);
           u.setCorreo(Correo);
           u.setPassword(Contra);
            db.ActualizarUsuario(u, Usuario);
            sesion.removeAttribute("usuario");
            sesion.setAttribute("usuario",u.getNombre());
        }catch(Exception ex){
           response.sendRedirect("Error404.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    
}
