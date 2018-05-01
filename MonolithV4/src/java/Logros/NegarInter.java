/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logros;

import Objetos.Intercambio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BaseDatos.DataBase;
import BaseDatos.Database2;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "NegarInter", urlPatterns = {"/NegarInter"})
public class NegarInter extends HttpServlet {

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
            int IDInter = Integer.parseInt(request.getParameter("id"));
            Intercambio me = new Intercambio();

            Calendar fechita = new GregorianCalendar();
            int año = fechita.get(Calendar.YEAR);
            int mes = fechita.get(Calendar.MONTH);
            int dia = fechita.get(Calendar.DAY_OF_MONTH);
            int meschido = mes + 1;
            String fecha1 = "" + año + "-" + meschido + "-" + dia;
            me.setIDInter(IDInter);
            me.setFecha(fecha1);
            try {
                Database2 db = new Database2();
                db.NegarIntercambio(me);
                out.println("<script>alert('Intercambio negado')</script>");
                out.println("<script>location.replace('LogrosPrue.jsp');</script>");

            } catch (Exception ex) {
                response.sendRedirect("Error404.jsp");
            }

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
