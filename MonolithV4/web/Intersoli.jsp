<%-- 
    Document   : Intersoli
    Created on : 28/03/2018, 12:31:45 PM
    Author     : memo0
--%>
<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/GruposCSS.css" rel="stylesheet" type="text/css">
        <link href="Css/estilosperros.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <%
            HttpSession sesion = request.getSession();
            
            if (sesion.getAttribute("usuario") == null) {
                out.println("<script>location.replace('index.html');</script>");
            } else {
                String usuario = sesion.getAttribute("usuario").toString();
        %>
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
                    <li class="nav-item">
                        <a class="nav-link"  href="Logros.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Logros</a>
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

            <div class="row ">
                <div class="col-12">
                    <center><h1>Solicitudes de <%=usuario%></h1></center>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <a type="button" class="btn btn-primary" href="Inter.jsp">Regresar</a>
                    <h3>Solicitudes:</h3>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div id="acordion" role="tablist" aria-multiselectable="true">
                        <%
                            int IDusuario = 0;
                            Connection con = null;
                            Statement sta = null;
                            ResultSet r = null;
                            try {
                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                                sta = con.createStatement();
                                int idusuda = 0;
                                int idusure = 0;
                                String usuda = "";
                                String usure = "";
                                int iddogoda = 0;
                                int iddogore = 0;
                                String dogoda = "";
                                int dogore = 0;
                                String filtroDa = "";
                                String estado = "";
                                String fecha = "";
                                int conta = 0;
                                String tipo = "";
                                String memo = "";
                                int imgchida = 0;
                                r = sta.executeQuery("select * from Intercambio where UsuarioDa='" + usuario + "' or UsuarioRe='" + usuario + "'  order by IDInter DESC  ");
                                while (r.next()) {

                                    conta++;
                                    idusuda = Integer.parseInt(r.getString("IDusuarioDa"));
                                    idusure = Integer.parseInt(r.getString("IDusuarioRe"));
                                    usuda = r.getString("UsuarioDa");
                                    usure = r.getString("UsuarioRe");
                                    iddogoda = Integer.parseInt(r.getString("IDdogoDa"));
                                    iddogore = Integer.parseInt(r.getString("IDdogoRe"));
                                    filtroDa = r.getString("FiltroDa");
                                    imgchida = Integer.parseInt(r.getString("ImgdogoDa"));
                                    dogoda = r.getString("dogoDa");
                                    dogore = Integer.parseInt(r.getString("dogoRe"));
                                    estado = r.getString("Estado");
                                    fecha = r.getString("fecha");
                                    if (estado.equals("proceso")) {
                                        tipo = "primary";
                                    } else {
                                        if (estado.equals("acepetada")) {
                                            tipo = "success";
                                        } else {
                                            if (estado.equals("negado")) {
                                                tipo = "danger";
                                            }
                                        }
                                    }
                                    if (usuda.equals(usuario)) {
                                        memo = "Intercambio";
                                    } else {
                                        if (usure.equals(usuario)) {
                                            memo = "Solicitud";
                                        }
                                    }

                        %>
                        
                            <div class="card">
                                <div class="card-header" role="tab" id="heading<%=conta%>">
                                    <h5 class="mb-0">
                                        <a href="#collapse<%=conta%>" data-toggle="collapse" data-parent="#acordion" aria-expanded="true" aria-controls="collapse<%=conta%>">
                                            <%=memo%> hacia <p id="usuda" name="usuda"><%=usure%></p>
                                        </a>
                                    </h5>

                                </div>

                                <div id="collapse<%=conta%>" class="collapse" role="tabpanel" aria-labelledby="heading<%=conta%>">
                                    <div class="card-body">
                                        <p id="fecha" name="fecha"><%=fecha%></p>
                                        <%=usuda%> ofrece el<p id="dogoda" name="dogoda"><%=dogoda%></p>
                                        a <%=usure%> por el dogo#<p id="dogore" name="dogoda"><%=dogore%></p>
                                        Estado:<a type="button" class="btn btn-<%=tipo%>"><%=estado%></a>


                                    </div>
                                    <div class="col-4">
                                        <img src="img/dogo<%=imgchida%>.jpg" class="card-img-top img-fluid <%=filtroDa%>" alt="" width=250px" height="250px">

                                    </div>
                                </div>
                            </div>
                        
                        <%}
                                } catch (SQLException error) {
                                    out.print(error.toString());
                                }
                            }
                        %>


                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
