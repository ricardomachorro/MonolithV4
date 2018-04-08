<%-- 
    Document   : SoliReal
    Created on : 28/03/2018, 04:55:48 PM
    Author     : memo0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            HttpSession sesion = request.getSession();

            if (sesion.getAttribute("usuario") == null) {
                out.println("<script>location.replace('index.html');</script>");
            } else {
                String usuario = sesion.getAttribute("usuario").toString();
        %>
    </head>
    <body>
        <%
                int IDdodoDa = 0;
                int IDdogoRe = Integer.parseInt(request.getParameter("IDimg"));
                int IDInter = Integer.parseInt(request.getParameter("id"));
                int img1 = 0;
                int img2 = 0;
                int filtro1 = 0;
                int filtro2 = 0;
                String nombre1 = "";
                String nombre2 = "";
                Calendar fechita = new GregorianCalendar();
                int año = fechita.get(Calendar.YEAR);
                int mes = fechita.get(Calendar.MONTH);
                int dia = fechita.get(Calendar.DAY_OF_MONTH);
                int meschido = mes + 1;
                String fecha1 = "" + año + "-" + meschido + "-" + dia;
                try {
                    Connection con = null;
                    Statement sta = null;
                    ResultSet r = null;
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                    sta = con.createStatement();
                    sta.executeUpdate("update Intercambio set IDdogoRe=" + IDdogoRe + " where  IDInter= " + IDInter + ";");
                    r = sta.executeQuery("select * from Intercambio where IDInter=" + IDInter + "");
                    while (r.next()) {
                        IDdodoDa = Integer.parseInt(r.getString("IDdogoDa"));
                    }
                    r = sta.executeQuery("select * from Logro where IDLogro=" + IDdodoDa + "");
                    while (r.next()) {
                        img1 = Integer.parseInt(r.getString("Img"));
                        filtro1 = Integer.parseInt(r.getString("Filtro"));
                        nombre1 = r.getString("Nombre");
                    }
                    r = sta.executeQuery("select * from Logro where IDLogro=" + IDdogoRe + "");
                    while (r.next()) {
                        img2 = Integer.parseInt(r.getString("Img"));
                        filtro2 = Integer.parseInt(r.getString("Filtro"));
                        nombre2 = r.getString("Nombre");
                    }
                    sta.executeUpdate("update Intercambio set Estado='aceptada',fecha='" + fecha1 + "' where IDInter = " + IDInter + ";");
                    sta.executeUpdate("update Logro set Img=" + img1 + ",Filtro=" + filtro1 + ",Nombre='" + nombre1 + "',fecha='" + fecha1 + "' where  IDLogro= " + IDdogoRe + ";");
                    sta.executeUpdate("update Logro set Img=" + img2 + ",Filtro=" + filtro2 + ",Nombre='" + nombre2 + "',fecha='" + fecha1 + "' where  IDLogro= " + IDdodoDa + ";");
                    out.println("<script>alert('Intercambio realizado exitosamente')</script>");
                    out.println("<script>location.replace('Logros.jsp');</script>");
                } catch (SQLException error) {
                    out.print(error.toString());
                }
            }
        %>
    </body>
</html>
