/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BaseDatos.Database2;
import Objetos.Actividad;
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
import javax.json.JsonWriter;

@WebServlet(name = "IngresarActividad", urlPatterns = {"/IngresarActividad"})
public class IngresarActividad extends HttpServlet {

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
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            HttpSession s = request.getSession();
            String Usuario = s.getAttribute("usuario").toString();
            String Clase = request.getParameter("CategoriaActividad");
            String Titulo = request.getParameter("NombreActivity");
            String Fecha=request.getParameter("FechaActividad");
            Actividad act = new Actividad();
            act.setCategoria(Clase);
            act.setUsuario(Usuario);
            act.setTitulo(Titulo);
            Database2 db = new Database2();
            db.IngresoActividad(act);
          

        } catch (Exception ex) {
              System.out.println(ex.toString());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
