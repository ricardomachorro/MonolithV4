<%-- 
    Document   : negInter
    Created on : 12/05/2018, 06:39:38 PM
    Author     : INSPIRON
--%>

<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                String usuario = request.getParameter("usuario");
                String link = "Ofertas.jsp?usuario=" + usuario;
                int IDInter = Integer.parseInt(request.getParameter("id"));
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
                    //sta.executeUpdate("Insert into Logro(IDUsuario,Img,Filtro,Nombre,fecha) values("+IDusuario+"," + img + "," + fil + ",'perro #" + n + "','" + fecha1 + "')");
                    sta.executeUpdate("update Intercambio set Estado='negado',fecha='" + fecha1 + "' where IDInter = " + IDInter + ";");
                    out.println("<script>location.replace('" + link + "');</script>");
                } catch (SQLException error) {
                    out.print(error.toString());
                }
                out.println("<script>location.replace('" + link + "');</script>");
            

        %>
    </body>
</html>
