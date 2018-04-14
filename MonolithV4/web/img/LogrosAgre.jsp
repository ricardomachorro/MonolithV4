<%-- 
    Document   : agregar
    Created on : 6/03/2018, 11:58:35 AM
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
                Random rand = new Random();
                int n = rand.nextInt(100) + 1;
                int img=rand.nextInt(7) + 1;
                int fil=rand.nextInt(11) + 1;
                Calendar fechita = new GregorianCalendar();
                int año = fechita.get(Calendar.YEAR);
                int mes = fechita.get(Calendar.MONTH);
                int dia = fechita.get(Calendar.DAY_OF_MONTH);
                String fecha1 = "" + año + "-" + mes + "-" + dia;
                int costo1 = 5;

                try {
                    Connection con = null;
                    Statement sta = null;
                    ResultSet r = null;
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                    sta = con.createStatement();
                    sta.executeUpdate("Insert into Logro(Img,Filtro,Nombre,fecha,costo) values(" + img + "," + fil + ",'perro #" + n + "','" +fecha1 +"'," + costo1 + ")");
                    out.println("<script>location.replace('Logros.jsp');</script>");
                } catch (SQLException error) {
                    out.print(error.toString());
                }

        %>
    </body>
</html>
