<%-- 
    Document   : CerrarSesion
    Created on : 27/03/2018, 03:47:20 PM
    Author     : memo0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession seci = request.getSession();
            seci.setAttribute("nivel", null);
            seci.setAttribute("user", null);
            session.invalidate();
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>
