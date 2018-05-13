package Servlets;

import BaseDatos.Database2;
import Objetos.Usuario;
import java.io.*;
import java.net.InetAddress;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Email;

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
            /*
            Nombre=request.getParameter("nombre").toString();
            Edad = Integer.parseInt(request.getParameter("edad").toString());
            Pais = request.getParameter("pais").toString();
            Direccion =request.getParameter("direccion").toString();
            Correo = request.getParameter("correo").toString();
            Password =  request.getParameter("contra").toString();*/
            Nombre = request.getParameter("NomUsuario");
            String age = request.getParameter("Age");
            Edad = Integer.parseInt(age);
            Pais = request.getParameter("PaisUsuario");
            Direccion = request.getParameter("DirUsuario");
            Correo = request.getParameter("CorreoUsuario");
            Password = request.getParameter("ContraUsuario");
            u.setNombre(Nombre);
            u.setEdad(Edad);
            u.setPais(Pais);
            u.setDireccion(Direccion);
            u.setCorreo(Correo);
            u.setPassword(Password);
            u.setValidado("No");
            if (db.IngresoUsuario(u)) {
                String ParametrosHash = u.getNombre() + u.getPassword();
                int HashCode = ParametrosHash.hashCode();
                int adendum;
                if (HashCode < 0) {
                    adendum = HashCode * -1;
                } else {
                    adendum = HashCode;
                }
                sesion.setAttribute("correo", u.getCorreo());
                //sesion.setAttribute("usuario", u.getNombre());
                //sesion.setAttribute("password", u.getPassword());
                //response.sendRedirect("Actividades.jsp");
                
            Email email = new Email();

            String de = "hawkward.ipn@gmail.com";
            String clave = "TheH4wK_fl1l7*5";
            String para =u.getCorreo();
            String mensaje = "Se ha enviado el siguiente código de verificación: " + adendum + ", por favor ingreselo en la página.\n\n" + "Si recibió este correo por equivocación por favor ignorelo.\n\n" + "Gracias.";
            String asunto = "Verificación de Correo Electrónico";
                
            boolean resultado = email.enviarCorreo(de, clave, para, mensaje, asunto);
            
            if(resultado){
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Ok");
            }else{
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("ErrorCorreo");
            }
            
                 
            } else if (db.UsuarioRepetido(u)) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("UsuarioRepetido");
            } else if (db.CorreoRepetido(u)) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("CorreoRepetido");
            }

        } catch (Exception ex) {
            response.sendRedirect("Error404.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
