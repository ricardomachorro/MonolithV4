<%-- 
    Document   : Logros
    Created on : 2/03/2018, 10:53:58 AM
    Author     : Alumno
--%>
<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logros</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/GruposCSS.css" rel="stylesheet" type="text/css">
        <link href="Css/estilosperros.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light  BarraDeInicio">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="true" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand"><img src="img/pawn.svg" id="LogoBarraInicio"></a>

            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link"  href="Actividades.jsp"><img src="img/signing-the-contract.svg" class="ImagenesBarraInicio" >Actividades</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href=""><img src="img/group.svg" class="ImagenesBarraInicio" >Grupo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href=""><img src="img/post-it.svg" class="ImagenesBarraInicio" >Notas</a>
                    </li>

                </ul>
                <ul class="navbar-nav mr-left mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <img src="img/user.svg" class="ImagenesBarraInicio" > Usuario
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink" style="align-content:center;">
                            <a class="dropdown-item" href="CerrarSesion.jsp"><img src="img/enter.svg" class="ImagenesBarraInicio" > Cerrar Sesion</a>
                            <a class="dropdown-item" href="Configuracion.jsp"><img src="img/settings-work-tool.svg" class="ImagenesBarraInicio" >
                                Configuracion</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container"> 
            <div class="row">
                <div class="col">
                    <form action="LogrosAgre.jsp" method="post">
                        <input type="submit" class="btn btn-dark" value="Agregar">
                    </form>
                </div>
            </div>
            <div class="row mt-3 mb-3">

                <div class="col">
                    <div class="card-columns">

                        <%
                            Connection con = null;
                            Statement sta = null;
                            ResultSet r = null;
                            String nombre = "";
                            String fecha = "";
                            String perrito="";
                            int numpe=0;
                            String fil="";
                            int costo = 0;
                            try {
                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                                sta = con.createStatement();
                                r = sta.executeQuery("select * from Logro");
                                while (r.next()) {
                                    numpe=Integer.parseInt(r.getString("Img"));
                                    fil="filtro"+r.getString("Filtro");
                                    perrito="dogo"+numpe+".jpg";
                                    nombre = r.getString("Nombre");
                                    fecha = r.getString("fecha");
                                    costo = Integer.parseInt(r.getString("Costo"));
                                    
                        %>
                        <div class="card">
                            <img src="img/<%=perrito%>" class="card-img-top <%=fil%>" alt="" width=250px" height="250px">
                            <div class="card-body">
                                <h5 class="card-title"><%=nombre%></h5>
                                <p>El dogo se consiguio el <%=fecha%></p>
                                <p>Este dogo tiene un costo de <%=costo%> actividades</p>
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
