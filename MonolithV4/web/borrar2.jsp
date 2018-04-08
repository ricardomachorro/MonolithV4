<%-- 
    Document   : borrar2
    Created on : 7/04/2018, 08:15:10 PM
    Author     : Toshiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@page import="java.sql.*, java.io.*" %>
        <%
            String titu;
            Connection c = null;
            Statement s = null;
            ResultSet r = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                c = DriverManager.getConnection("jdbc:mysql://localhost/proyecto", "root", "n0m3l0");
                s = c.createStatement();
            } catch (SQLException error) {
                out.println(error.toString());
            }
            titu = request.getParameter("tit");
            r = s.executeQuery("select * from Notas where Nombrenot='" + titu + "';");
            if (!r.next()) {
                out.println("<script>alert('El folio no existe carnalito')");

                c.close();
                out.println("window.location='Notas.jsp'");
                out.println("</script>");
            } else {
                try {
                    s.executeUpdate("delete from Notas where Nombrenot='" + titu + "';");
                    out.println("<script>alert('dato eliminado')");
                    c.close();
                    out.println("window.location='Notas.jsp'");
                    out.println("</script>");
                } catch (SQLException error) {
                    out.println(error.toString());
                }
            }

        %>

    </body>
</html>
