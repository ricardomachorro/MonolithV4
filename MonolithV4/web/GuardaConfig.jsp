<%-- 
    Document   : GuardaConfig
    Created on : 7/04/2018, 07:10:11 PM
    Author     : Alex
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardar Cambios</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            String nombreSesion = sesion.getAttribute("NombreUsuario").toString();
           
            Connection con = null;
            Statement sta = null;
            String nombre = request.getParameter("nombre"); 
            String edad = request.getParameter("edad");
            String correo = request.getParameter("correo");
            String pais = request.getParameter("pais");
            String contra = request.getParameter("contra");
            String direccion = request.getParameter("direccion");

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                sta = con.createStatement();
            } catch (SQLException error) {
                out.print(error.toString());
            }
            ////////////////////////////////////////////////////////////////////////////////////
            try{
            sta.executeUpdate("update Usuario set NombreUsuario='"+nombre+"',Correo='"+correo+"',Edad="+edad+",Pais='"+pais+"',Direccion='"+direccion+"',Contrasena='"+contra+"' where NombreUsuario='"+nombreSesion+"';");
            sesion.setAttribute("NombreUsuario", nombre);
            out.println("<script>alert('Datos guardados correctamente.');</script>");
            out.println("<script>window.location='Actividades.jsp';</script>");
           // out.print("META HTTP-EQUIV='REFRESH' CONTENT='.0000001; URL=http://localhost:8080/Altas2/'/>");  // para que sirve esta linea?
        }
        catch (SQLException error)
        {
        out.println(error.toString());
        }
        %>
    </body>
</html>
