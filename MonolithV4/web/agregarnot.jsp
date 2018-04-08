<%-- 
    Document   : agregarnot
    Created on : 19/03/2018, 08:17:31 PM
    Author     : Toshiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="iso-8859-1">
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>JSP Page</title>
    </head>
    <body>
         <%@page import="java.sql.*, java.io.*" %>
         <%
            String tit=request.getParameter("titulo"); 
            String not = request.getParameter("nota");
             
            tit = tit.replaceAll("<", "&lt;");
            tit = tit.replaceAll(">", "&gt;");
            not = not.replaceAll("<", "&lt;");
            not = not.replaceAll(">", "&gt;");
            

            Connection con = null;
            Statement sta = null;
            PreparedStatement pstatement = null;
            int updateQuery = 0;
     
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                sta = con.createStatement();
            } catch (SQLException error) {
                out.println(error.toString());
            }

            try {
              String queryString="INSERT INTO Nota(Nombrenot,Contenido) values(?,?)";
              pstatement = con.prepareStatement(queryString);
              
              pstatement.setString(1, tit);
              pstatement.setString(2, not);
              updateQuery = pstatement.executeUpdate();
                out.println("<script>alert('Nota guardada  >:v')");
                con.close();
                out.println("window.location='Notas.jsp'");
                out.println("</script>");
            } catch (SQLException error) {
                out.println("Hola este mensaje este mensaje es para informarte que algo murio :v <br><br>");
                 con.close();
                out.println("<a href='Notas.jsp'>Regresar</a>");

            }
            con.close();
         %>
        
    </body>
</html>
