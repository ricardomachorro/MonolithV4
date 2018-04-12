/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BaseDatos.Database2;
import Objetos.Grupo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "AgregarMiembro", urlPatterns = {"/AgregarMiembro"})
public class AgregarMiembro extends HttpServlet {

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
            //Inicializo la bd
            Database2 db = new Database2();
            //Para las variables de sesion
            HttpSession s = request.getSession();
            //Objeto grupo
            Grupo grupo = new Grupo();
            
            //Traigo el parametro correoMiembro de la data de ajax
            String correoMiembro = request.getParameter("correoMiembro");
            //Traigo el nombre del usuario de la bd buscandolo por su correo
            String nombreMiembro = db.consultarMiembro(correoMiembro);
            
            grupol.set
            
            //Envio el nombre del usuario/miembro
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(nombreMiembro);
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}