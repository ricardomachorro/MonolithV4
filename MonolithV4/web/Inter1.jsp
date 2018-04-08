<%-- 
    Document   : Inter1
    Created on : 27/03/2018, 10:30:04 PM
    Author     : memo0
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                int IDusuarioDa = 0;
                int IDusuarioRe = 0;
                int IDimg = 0;
                int numRe = 0;
                String filtro = "";
                try {
                    String numDa = request.getParameter("memo");
                    String UsuRe = request.getParameter("UsuarioRe");
                    //esta mamada >:V
                    numRe = Integer.parseInt(request.getParameter("numRe"));

                    IDimg = Integer.parseInt(request.getParameter("IDimg"));

                    filtro = request.getParameter("filtro");
                    Calendar fechita = new GregorianCalendar();
                int año = fechita.get(Calendar.YEAR);
                int mes = fechita.get(Calendar.MONTH);
                int dia = fechita.get(Calendar.DAY_OF_MONTH);
                int meschido = mes + 1;
                String fecha1 = "" + año + "-" + meschido + "-" + dia;
                String estado = "proceso";
                String prueba = numDa.substring(4, 5);
                String chido = "";
                int img1 = 0;
                if (usuario.equals(UsuRe)) {
                    out.println("<script>alert('Usuario no valido')</script>");
                    out.println("<script>location.replace('Inter.jsp');</script>");
                } else {
                    try {
                        Connection con = null;
                        Statement sta = null;
                        ResultSet r = null;
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                        sta = con.createStatement();
                        r = sta.executeQuery("select * from Usuario where NombreUsuario='" + usuario + "'");
                        if (r.next()) {
                            IDusuarioDa = Integer.parseInt(r.getString("IDUsuario"));
                        }
                        r = sta.executeQuery("select * from Logro where IDLogro='" + IDimg + "'");
                        while (r.next()) {
                            chido = r.getString("Nombre");
                            img1 = Integer.parseInt(r.getString("Img"));
                        }
                        r = sta.executeQuery("select * from Usuario where NombreUsuario='" + UsuRe + "'");
                        if (r.next()) {
                            IDusuarioRe = Integer.parseInt(r.getString("IDUsuario"));
                            sta.executeUpdate("Insert into Intercambio(IDusuarioDa,IDusuarioRe,UsuarioDa,UsuarioRe,FiltroDa,IDdogoDa,IDdogoRe,ImgdogoDa,dogoDa,dogoRe,Estado,fecha) values(" + IDusuarioDa + "," + IDusuarioRe + ",'" + usuario + "','" + UsuRe + "','" + filtro + "'," + IDimg + ",0," + img1 + ",'" + chido + "'," + numRe + ",'" + estado + "','" + fecha1 + "')");
                            out.println("<script>alert('Oferta hecha con exito')</script>");
                        } else {
                            out.println("<script>alert('Usuario no valido')</script>");
                            out.println("<script>location.replace('Inter.jsp');</script>");
                        }

                        out.println("<script>location.replace('Inter.jsp');</script>");
                    } catch (SQLException error) {
                        out.print(error.toString());

                    }

                }
                } catch (java.lang.NumberFormatException e) {
                    out.println("<script>alert('Dogo no seleccionado')</script>");
                    out.println("<script>location.replace('Inter.jsp');</script>");
                }

                
            }


        %>
    </body>
</html>
