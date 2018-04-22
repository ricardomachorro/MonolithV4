package Servlets;

import java.io.*;
import java.net.InetAddress;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import BaseDatos.Database2;
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
        String nombre = request.getParameter("NomUsuarioCom");
        String Codigo = request.getParameter("CodigoCom");
        String Contra = request.getParameter("ContraCom");
        String ParametrosHash = nombre + Contra;
        int HashCode = ParametrosHash.hashCode();
        int adendum;
        if (HashCode < 0) {
            adendum = HashCode*-1;
        } else {
            adendum = HashCode;
        }
        try{
            Database2 db=new Database2();
        if(db.ResultadoFinalValidacion(nombre).equalsIgnoreCase(Codigo) && Integer.toString(adendum).equalsIgnoreCase(Codigo)){
            db.DarValidoUsuario(nombre);
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", nombre);
            sesion.setAttribute("password", Contra);
            response.setContentType("text/plain");
             response.setCharacterEncoding("UTF-8");
             response.getWriter().write("UsuarioValidado");
        }else{
             response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("UsuarioNoValidado");
        }
        }catch(Exception ex){
            
        }
        

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
