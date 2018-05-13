package Servlets;

import BaseDatos.Database2;
import Objetos.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class CambiarTarea extends HttpServlet {

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
            //Inicializo el objeto tarea
            Tarea t = new Tarea();
            //Traigo los parametros de la data de ajax
            String Tarea = request.getParameter("idTarea");
            String nuevoNombre = request.getParameter("newNombre");
            String nuevoMiembro = request.getParameter("newMiembro");
            String nuevaFecha = request.getParameter("newFecha");
            String eliminarMiembro = request.getParameter("byeMiembro");
            
            //Datos faltantes por convertir
            int IDT = Integer.parseInt(Tarea);
            Date fechaT = Date.valueOf(nuevaFecha);
            int IDM;
            //Lleno el objeto tarea
            t.setIdTarea(IDT);
            t.setNombreTarea(nuevoNombre);
            t.setFechaTarea(fechaT);
            
            db.actualizarTarea(t);
            
            if(nuevoMiembro.equals("")) {
                IDM = db.IdentificarUsuario(eliminarMiembro);
                db.desasignarMiembro(IDT, IDM);
            } else if(eliminarMiembro.equals("")) {
                IDM = db.IdentificarUsuario(nuevoMiembro);
                db.asignarMiembro(IDT, IDM);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}


