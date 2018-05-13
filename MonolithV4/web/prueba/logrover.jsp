<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logros</title>
        <link href="../Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="../Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="../Css/GruposCSS.css" rel="stylesheet" type="text/css">
        <link href="../Css/estilosperros.css" rel="stylesheet" type="text/css">
        <script src="../js/jquery-3.2.1.min.js"></script>
        <script src="../js/popper.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.validate.js"></script>
        <%
            String usuario = request.getParameter("usuario");
        %>
    </head>
    <body style="background-color: #fff">

        <%
            int IDusuario = 0;
            Connection con = null;
            Statement sta = null;
            ResultSet r = null;
            String nombre = "";
            String fecha = "";
            String perrito = "";
            int numpe = 0;
            String fil = "";
            int puntos = 0;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                sta = con.createStatement();
                r = sta.executeQuery("select * from Usuario where NombreUsuario='" + usuario + "'");
                if (r.next()) {
                    IDusuario = Integer.parseInt(r.getString("IDUsuario"));
                    puntos = Integer.parseInt(r.getString("Puntos"));
                }

        %>
        <div class="container-fluid">

            <div class="row ">

                <div class="col">
                    <div class="card CartasResumenActividades" >
                        <div class="card-body">
                            <div class="row">

                                <div class="col-12 TextoCartasResumenActividades" >
                                    <center><a >Tienes <%=puntos%> puntos</a></center>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-columns">

                        <%
                            r = sta.executeQuery("select * from Logro where IDUsuario='" + IDusuario + "'");
                            int filaux;
                            String perritoaux;
                            while (r.next()) {
                                numpe = Integer.parseInt(r.getString("Img"));
                                filaux = Integer.parseInt(r.getString("Filtro"));
                                if (filaux < 10) {
                                    fil = "filtro0" + r.getString("Filtro");
                                } else {
                                    fil = "filtro" + r.getString("Filtro");
                                }
                                if (numpe < 10) {
                                    perrito = "dogo0" + numpe + ".jpg";
                                } else {
                                    perrito = "dogo" + numpe + ".jpg";
                                }
                                nombre = r.getString("Nombre");
                                fecha = r.getString("fecha");

                        %>
                        <div class="card">
                            <img src="../img/<%=perrito%>" class="card-img-top <%=fil%>" alt="" width=150px" height="150px">
                            <div class="card-body">
                                <h5 class="card-title"><%=nombre%></h5>
                                <p>El dogo se consiguio el <%=fecha%></p>

                            </div>
                        </div>

                        <%   }
                            } catch (SQLException error) {
                                out.print(error.toString());
                            }

                        %>


                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
