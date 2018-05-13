/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BaseDatos.Database2;
import Objetos.Tarea;
import java.sql.Date;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "AgregarTarea", urlPatterns = {"/AgregarTarea"})
public class AgregarTarea extends HttpServlet {
    private int idTarea;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //https://stackoverflow.com/questions/4112686/how-to-use-servlets-and-ajax
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Envio la id de la tarea
            idTarea = db.traerIDTarea();
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
        }
        String id = Integer.toString(idTarea);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Inicializo la bd
            Database2 db = new Database2();
            //Inicializo el objeto tarea
            Tarea t = new Tarea();
            //Traigo los parametros de la data de ajax
            String nombreTarea = request.getParameter("nomTarea");
            String nombreGrupo = request.getParameter("nomGrupo");
            String fechaTarea = request.getParameter("fechaTarea");
            String miembro = request.getParameter("nomMiembro");
            
            //Datos faltantes por convertir
            int idGrupo;
            int idMiembro;
            Date fechaT = Date.valueOf(fechaTarea);
            
            //Combierto el nombre del grupo a la id del grupo para el insert alv
            idGrupo = db.IDGrupo(nombreGrupo);
            //Traigo la id del miembro para asignarlo a la tarea
            idMiembro = db.IdentificarUsuario(miembro);
            
            //Lleno el objeto tarea
            t.setNombreTarea(nombreTarea);
            t.setIdGrupoTarea(idGrupo);
            t.setFechaTarea(fechaT);
            t.setIdMiembroTarea(idMiembro);
            
            //Creo la tarea (Al mismo tiempo se le asigna el miembro)
            db.crearTarea(t);
            
        } catch (Exception e) {
            System.out.println("Error: " + e + " :'v");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

