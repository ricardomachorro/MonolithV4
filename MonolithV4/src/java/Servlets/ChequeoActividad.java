/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BaseDatos.Database2;
import Objetos.Actividad;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(name = "ChequeoActividad", urlPatterns = {"/ChequeoActividad"})
public class ChequeoActividad extends HttpServlet {
    
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
        try{
            Database2 db=new Database2();
            String Opccion=request.getParameter("Opccion");
            String IDActividad=request.getParameter("IDActividad");
            HttpSession s = request.getSession();
            String Usuario = s.getAttribute("usuario").toString();
            if(Opccion.equalsIgnoreCase("1")){  
            int IDActReal=Integer.parseInt(IDActividad);
            db.CambiarEstadoActividad(IDActReal); 
            int ActividadesHechas=db.ContadorActividadesFinalizadas(Usuario);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(Integer.toString(ActividadesHechas));
            }else if(Opccion.equalsIgnoreCase("2")){
                int ActividadesNoHechas=db.ContadorActividadesFaltantes(Usuario);
              response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(Integer.toString(ActividadesNoHechas));
            }
            
       

        } catch (Exception ex) {
              System.out.println(ex.toString());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
