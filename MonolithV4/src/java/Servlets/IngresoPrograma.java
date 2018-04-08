
package Servlets;

import BaseDatos.Database2;
import Objetos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IngresoPrograma", urlPatterns = {"/IngresoPrograma"})
public class IngresoPrograma extends HttpServlet {
    
    private String Nombre;
    private String Contraseña;
    
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
            Nombre=request.getParameter("usuario").toString();
            Contraseña =  request.getParameter("contrasenia").toString();
            u.setNombre(Nombre);
            u.setPassword(Contraseña);
            if(db.IngresoPrograma(u)){
                sesion.setAttribute("usuario", u.getNombre()); 
                sesion.setAttribute("NombreUsuario", u.getNombre());
                sesion.setAttribute("password",u.getPassword());
                response.sendRedirect("Actividades.jsp");
            }else{
                 response.sendRedirect("Error404.jsp");
            }
            
           
        }catch(Exception ex){
           response.sendRedirect("Error404.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
    
