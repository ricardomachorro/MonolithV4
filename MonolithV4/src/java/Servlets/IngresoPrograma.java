
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
            //BD
            Database2 db = new Database2();
            //SESION
            HttpSession sesion = request.getSession();   
            this.Nombre = request.getParameter("usuario").toString();
            this.Contraseña =  request.getParameter("contrasenia").toString();
            //OBJETO
            u.setNombre(Nombre);
            u.setPassword(Contraseña);
            
            if(db.IngresoPrograma(u)){ //Evalua el ingreso con datos correctos
                //Se agregan varibles de sesion
                sesion.setAttribute("usuario", u.getNombre());
                sesion.setAttribute("password", u.getPassword());
                response.sendRedirect("grupos.jsp");//Abre programa (En grupos xd :v/, El principal deberia ser Actividades)
            }else{
                 response.sendRedirect("Error404.jsp");
            }

        } catch(Exception ex){
           response.sendRedirect("Error404.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
    
