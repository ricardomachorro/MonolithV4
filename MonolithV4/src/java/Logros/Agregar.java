/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logros;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import BaseDatos.DataBase;
import BaseDatos.Database2;
import Objetos.Logro;
import Objetos.Usuario;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.json.JsonObject;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "Agregar", urlPatterns = {"/Agregar"})
public class Agregar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Agregar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Agregar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        Logro l = new Logro();

        try {
            Database2 db = new Database2();
            HttpSession sesion = request.getSession();
            String Usuario = sesion.getAttribute("usuario").toString();
            int IDusuario = db.IDusu(Usuario);
            Random rand = new Random();
            int n = rand.nextInt(100) + 1;
            int img = rand.nextInt(7) + 1;
            int fil = rand.nextInt(10) + 1;
            Calendar fechita = new GregorianCalendar();
            int año = fechita.get(Calendar.YEAR);
            int mes = fechita.get(Calendar.MONTH);
            int dia = fechita.get(Calendar.DAY_OF_MONTH);
            int meschido = mes + 1;
            String fecha1 = "" + año + "-" + meschido + "-" + dia;
            int costo1 = 0;
            l.setIDusuario(IDusuario);
            l.setN(n);
            l.setImg(img);
            l.setFil(fil);
            l.setFecha(fecha1);
            //db.(l);
            sesion.removeAttribute("usuario");
            sesion.setAttribute("usuario",Usuario);
        } catch (Exception ex) {
            response.sendRedirect("Error404.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
