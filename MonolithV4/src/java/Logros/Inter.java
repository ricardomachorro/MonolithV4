/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logros;

import BaseDatos.Database2;
import Objetos.Intercambio;
import Objetos.Logro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "Inter", urlPatterns = {"/Inter"})
public class Inter extends HttpServlet {

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
            Logro l = new Logro();
        int IDusuarioDa = 0;
        int IDusuarioRe = 0;
        int IDimg = 0;
        int numRe = 0;
        String filtro = "";
        HttpSession sesion = request.getSession();
        String usuario = sesion.getAttribute("usuario").toString();
        Intercambio me= new Intercambio();
        try {
            String numDa = request.getParameter("memop2").toString();
            String UsuRe = request.getParameter("UsuarioRe").toString();
            numRe = Integer.parseInt(request.getParameter("numRe").toString());
            IDimg = Integer.parseInt(request.getParameter("IDimg").toString());
            filtro = request.getParameter("filtro");
            Calendar fechita = new GregorianCalendar();
            int año = fechita.get(Calendar.YEAR);
            int mes = fechita.get(Calendar.MONTH);
            int dia = fechita.get(Calendar.DAY_OF_MONTH);
            int meschido = mes + 1;
            String fecha1 = "" + año + "-" + meschido + "-" + dia;
            String estado = "proceso";
            String chido = "";
            
            me.setDogoDa(numDa);
            me.setUsuarioRe(UsuRe);
            me.setDogoRe(numRe);
            me.setIdimg(IDimg);
            me.setFiltroDa(filtro);
            me.setFecha(fecha1);
            me.setEstado(estado);
            
            int img1 = 0;
            if (usuario.equals(UsuRe)) {
                 
            out.println("<script>alert('Usuario no valido')</script>");
            out.println("<script>location.replace('LogrosPrue.jsp');</script>");
                
            } else {
                try {
                    Database2 db = new Database2();
                    IDusuarioDa = db.IDusu(usuario);
                    me.setIDusuarioDa(IDusuarioDa);
                    me.setUsuarioDa(usuario);
                    db.Intercambio(me);
                    out.println("<script>alert('Oferta realizada con exito')</script>");
                    out.println("<script>location.replace('LogrosPrue.jsp');</script>");
                    sesion.removeAttribute("usuario");
                    sesion.setAttribute("usuario",usuario);
                } catch (Exception ex) {
                    response.sendRedirect("Error404.jsp");
                }
            }
        } catch (java.lang.NumberFormatException e) {
            out.println("<script>alert('Dogo no seleccionado')</script>");
            out.println("<script>location.replace('LogrosPrue.jsp');</script>");
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
        PrintWriter out = response.getWriter();
        /*
        Logro l = new Logro();
        int IDusuarioDa = 0;
        int IDusuarioRe = 0;
        int IDimg = 0;
        int numRe = 0;
        String filtro = "";
        HttpSession sesion = request.getSession();
        String usuario = sesion.getAttribute("usuario").toString();
        Intercambio me= new Intercambio();
        try {
            String numDa = request.getParameter("memop2").toString();
            String UsuRe = request.getParameter("UsuarioRe").toString();
            numRe = Integer.parseInt(request.getParameter("numRe").toString());
            IDimg = Integer.parseInt(request.getParameter("IDimg").toString());
            filtro = request.getParameter("filtro");
            Calendar fechita = new GregorianCalendar();
            int año = fechita.get(Calendar.YEAR);
            int mes = fechita.get(Calendar.MONTH);
            int dia = fechita.get(Calendar.DAY_OF_MONTH);
            int meschido = mes + 1;
            String fecha1 = "" + año + "-" + meschido + "-" + dia;
            String estado = "proceso";
            String chido = "";
            
            me.setDogoDa(numDa);
            me.setUsuarioRe(UsuRe);
            me.setDogoRe(numRe);
            me.setIdimg(IDimg);
            me.setFiltroDa(filtro);
            me.setFecha(fecha1);
            me.setEstado(estado);
            
            int img1 = 0;
            if (usuario.equals(UsuRe)) {
                 
            out.println("<script>alert('Usuario no valido')</script>");
            out.println("<script>location.replace('LogrosPrue.jsp');</script>");
                
            } else {
                try {
                    Database2 db = new Database2();
                    IDusuarioDa = db.IDusu(usuario);
                    me.setIDusuarioDa(IDusuarioDa);
                    me.setUsuarioDa(usuario);
                    db.Intercambio(me);
                    sesion.removeAttribute("usuario");
                    sesion.setAttribute("usuario",usuario);
                } catch (Exception ex) {
                    response.sendRedirect("Error404.jsp");
                }
            }
        } catch (java.lang.NumberFormatException e) {
            out.println("<script>alert('Dogo no seleccionado')</script>");
            out.println("<script>location.replace('LogrosPrue.jsp');</script>");
        }*/
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
