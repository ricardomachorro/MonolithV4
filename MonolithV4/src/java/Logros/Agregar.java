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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Logro l = new Logro();

            try {
                Database2 db = new Database2();
                HttpSession sesion = request.getSession();
                String Usuario = sesion.getAttribute("usuario").toString();
                int IDusuario = db.IDusu(Usuario);
                Random rand = new Random();
                int n = rand.nextInt(100) + 1;
                int img = rand.nextInt(14) + 1;
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
                if (db.Agregardogo(l, Usuario)) {
                    out.println("<script>location.replace('LogrosPrue.jsp');</script>");
                } else {
                    out.println("<script>alert('No tienes los puntos >:v')</script>");
                    out.println("<script>location.replace('LogrosPrue.jsp');</script>");
                }

                sesion.removeAttribute("usuario");
                sesion.setAttribute("usuario", Usuario);
            } catch (Exception ex) {
                response.sendRedirect("Error404.jsp");
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.sendRedirect("LogrosPrue.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
