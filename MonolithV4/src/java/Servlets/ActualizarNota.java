/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BaseDatos.Database2;
import java.io.IOException;
import Objetos.Nota;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ActualizarNota", urlPatterns = {"/ActualizarNota"})
public class ActualizarNota extends HttpServlet{
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
            Database2 db=new Database2();
            HttpSession s = request.getSession();
            Nota nota=new Nota();
            String Usuario=s.getAttribute("usuario").toString();
            String TituloNota=request.getParameter("TitleNota");
            String Contenido=request.getParameter("ContenidoNota");
            String AntiguoTitulo=request.getParameter("Anterior");
            nota.setUsuario(Usuario);
            nota.setTitulo(TituloNota);
            nota.setContenido(Contenido);
            db.ActualizarNota(nota, AntiguoTitulo);
            
            
        } catch (Exception ex) {
            String e=ex.toString();
              System.out.println(ex.toString());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
