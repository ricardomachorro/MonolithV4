<%-- 
    Document   : agrelogro
    Created on : 8/05/2018, 04:55:15 PM
    Author     : INSPIRON
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@page import="BaseDatos.Database2"%>
<%@page import="Objetos.Logro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String salida="";
            Logro l = new Logro();
            String Usuario=request.getParameter("nombre");
        try {
            Database2 db = new Database2();
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
                salida="Logro agregado";
            } else {
                salida="error al agregar dogo";
            }
        } catch (Exception ex) {
            salida="Error en la base de datos";
        }
        %>
        <h1></h1>
    </body>
</html>
