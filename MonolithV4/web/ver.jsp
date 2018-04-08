<%-- 
    Document   : ver
    Created on : 2/04/2018, 11:20:16 AM
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>por si acaso</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/NotasCSS.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>

    </head>
    <body>
        <%@page import="java.sql.*, java.io.*" %>
        <%
            String clav = request.getParameter("Nombrenot");

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
                clav = request.getParameter("Nombrenot");
                r = s.executeQuery("Select*from Nota ");
                out.println("<body>");
                out.println("<table  class='table'>");
                while (r.next()) {
                    String tit = r.getString("Nombrenot");
                    String not = r.getString("Contenido");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th scope='col'>" + tit + "</th>");
                    out.println("<th scope='col'>" + not + "</th>");
                    out.println("<th scope='col'><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal'>Modificar</button></th>");

                    out.println(" <div class='modal fade ' id='fm-modal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true' > ");

                    out.println("<div class='modal-dialog' role='document'>");
                    out.println("<div class='modal-content'>");
                    out.println("<div class='modal-header'>");
                    out.println("<h5 class='modal-title' id='exampleModalLabel'>Modificacion de datos de Notas</h5>");
                    out.println("<button class='close' data-dismiss='modal' aria-label='Close'>");
                    out.println("<span aria-hidden='true'>&times;</span>");
                    out.println("</button>");
                    out.println("</div>");

                    out.println("<div class='modal-body'>");

                    out.println("<form method='post' action='modificanot.jsp'>");
                    out.println("<input type='text' placeholder='Ingresa el nombre de la nota' value="+tit+" size='50' required/><br><br>");
                    out.println("<input type='text' placeholder='Ingresa la descripcion de la nota' value="+not+" size='50' required/><br><br>");
                    out.println("</div>");
                    out.println("<div class='modal-footer'>");
                    out.println("<input type='submit' value='Cambiar' class='btn btn-secondary' data-dismiss='modal'>");
                    
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<form action='borrar2.jsp' method='post'>");
                    out.println("<th scope='col' ><input type='submit value='Borrar' class='btn btn-secondary' >'</th>");
                    out.println("</form>");
                    out.println("</tr>");
                    out.println("</thead>");
                }
                out.println("</table>");

                out.println("</body>");
            } catch (SQLException error) {
                out.println(error.toString());
            }
            c.close();
        %>
    </body>
</html>
