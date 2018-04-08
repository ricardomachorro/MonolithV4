<%-- 
    Document   : consultarnot
    Created on : 7/04/2018, 08:09:27 PM
    Author     : Toshiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script>
            function borrar()
            {
                var result = confirm("desea en verdad borrar el registro");
                if (result === true)
                {
                    alert("registro borrado");
                    document.getElementById("borrar").submit;
                } else
                    alert("no se pudo borrar mi cuate");
            }
        </script>
    </head>
    <body>
        <%@page import="java.sql.*, java.io.*" %>
        <%
            String clav = request.getParameter("tit");

            Connection c = null;
            Statement s = null;
            ResultSet r = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                c = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                s = c.createStatement();

            } catch (SQLException error) {
                out.println(error.toString());
            }
            try {
                clav = request.getParameter("tit");
                r = s.executeQuery("select*from Notas where Nombrenot='" + clav.replace("\\", "\\\\").replace("'", "\\") + "';");

                if (r.next()) {
                    String tit = r.getString("Nombrenot");

                    String not = r.getString("Contenido");
                    out.println("Nombrenot:" + tit + "<br><br>");

                    out.println("Contenido: " + not + "<br><br>");

                    out.println("<form id='borrar' action='borrar2.jsp' method='post' name='borrar'>");
                    out.println("nombre: ");
                    out.println("<input type='text' name='clave' value='" + clav + "' readonly='readonly'>");
                    out.println("<input type='submit' name='bajas' value='bajas' class='button'>");
                    out.println("</form>");
                    out.println("<script> borrar();</script>");
                } else {
                    out.println("<script>alert('este mensaje nunca se desplegara')</script>");
                    out.println("<script>window.location='Notas.html'</script>");
                }

            } catch (SQLException error) {
                out.println(error.toString());
            }
        %>
    </body>
</html>
