package Servlets;

import BaseDatos.Database2;
import Objetos.Usuario;
import java.io.*;
import java.net.InetAddress;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;




@WebServlet(name = "IngresoUsuario", urlPatterns = {"/IngresoUsuario"})
public class IngresoUsuario extends HttpServlet {
    
    private String Nombre;
    private String Correo;
    private String Password;
    private int Edad;
    private String Pais;
    private String Direccion;
  
    
    
    
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
            Nombre=request.getParameter("nombre").toString();
            Edad = Integer.parseInt(request.getParameter("edad").toString());
            Pais = request.getParameter("pais").toString();
            Direccion =request.getParameter("direccion").toString();
            Correo = request.getParameter("correo").toString();
            Password =  request.getParameter("contra").toString();
            u.setNombre(Nombre);
            u.setEdad(Edad);
            u.setPais(Pais);
            u.setDireccion(Direccion);
            u.setCorreo(Correo);
            u.setPassword(Password);
            if(db.IngresoUsuario(u)){
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