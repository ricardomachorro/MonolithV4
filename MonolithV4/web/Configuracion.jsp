<%-- 
    Document   : Configuracion
    Created on : 7/04/2018, 06:20:05 PM
    Author     : Alex
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Configuración</title>
    <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
    <link href="Css/Actividades2CSS.css" rel="stylesheet" type="text/css">
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
                    <a class="nav-link"  href=""><img src="img/group.svg" class="ImagenesBarraInicio " >Grupo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href=""><img src="img/post-it.svg" class="ImagenesBarraInicio" >Notas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="Configuracion.jsp"><img src="img/settings-gears.svg" class="ImagenesBarraInicio" >Configuración</a>
                </li>  
            </ul>
            <ul class="navbar-nav mr-left mt-2 mt-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <img src="img/user.svg" class="ImagenesBarraInicio" > Usuario
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink" style="align-content:center;">
                        <a class="dropdown-item" >Usuario: <%/* 
                            out.println(Usuario);*/
                            %></a>
                        <a class="dropdown-item" href="CerrarSesion.jsp"><img src="img/enter.svg" class="ImagenesBarraInicio" > Cerrar Sesion</a>
                        <a class="dropdown-item" href="Configuracion.jsp"><img src="img/settings-work-tool.svg" class="ImagenesBarraInicio" >
                            Configuracion</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
    <%
        HttpSession sesion = request.getSession();
        String nombreSesion = sesion.getAttribute("NombreUsuario").toString();

        Connection con = null;
        Statement sta = null;
        ResultSet r = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
            sta = con.createStatement();
        } catch (SQLException error) {
            out.print(error.toString());
        }

        try {
            r = sta.executeQuery("select * from Usuario where NombreUsuario ='" + nombreSesion + "';");
            if (r.next()) {
                String edad = r.getString("Edad");
                String pais = r.getString("Pais");
                String direccion = r.getString("Direccion");
                String correoElectronico = r.getString("Correo");
                String contrasenia = r.getString("Contrasena");
    %>

    <form class="form-register " role="form" id="registro" method="POST" action="GuardaConfig.jsp" >
        <center><h2 class="form-titulo">Configuración de la cuenta</h2></center>
        <div class="contenedor-inputs container-fluid ">
            <center>
                <div class="form-group row" style="width:50%;">
                    <div class="col-12">
                        <input type="text" class=" form-control " value="<% out.println(nombreSesion); %>" style="width:100%;margin-top:3.5%;" id="nombre" name="nombre" maxlength="16" minlength="4" placeholder="NombreUsuario"  required>
                    </div>

                </div>
            </center>
            <center>
                <div class="form-group row" style="width:50%;">
                    <div class="col-12">
                        <input type="text" class="form-control" style="width:100%;margin-top:3.5%;"  id="numero" name="edad" value="<% out.println(edad); %>"  placeholder="Edad"  required>
                    </div>
                </div>
            </center>
            <center>
                <div class="form-group row" style="width:50%;">
                    <div class="col-12">
                        <input type="text" value="<% out.println(pais); %>" class="form-control" style="width:100%;margin-top:3.5%;"  id="pais" name="pais"  placeholder="Pais"  required>
                    </div>
                </div>
            </center>
            <center>
                <div class="form-group row" style="width:50%;">
                    <div class="col-12">
                        <input type="text" value="<% out.println(direccion); %>" class="form-control" style="width:100%;margin-top:3.5%;"  id="path" name="direccion" placeholder="Direccion"  required>
                    </div>
                </div>
            </center>
            <center>
                <div class="form-group row" style="width:50%;">
                    <div class="col-12">
                        <input type="email" value="<% out.println(correoElectronico); %>" class="form-control" style="width:100%;margin-top:3.5%;"  id="correo" name="correo"   placeholder="Correo"  required>
                    </div>
                </div>
            </center>
            <center>
                <div class="form-group row" style="width:50%;">
                    <div class="col-12">
                        <input type="text" value="<% out.println(contrasenia); %>" class="form-control" style="width:100%;margin-top:3.5%;"  id="contra" name="contra"  placeholder="Contraseña"  required>
                    </div>
                </div>
            </center>
            <center>
                <div class="col-12" style=" margin-top: 3%; padding-left:40%;padding-right:40%;">
                    <input class="registro"  type="submit" id="Envio" value="Guardar cambios">
                </div>
            </center>
        </div>
    </form>
    <%
            } else {
                out.println("<script>alert('Lo sentimos, hubo un error al inciar sesión.');</script>");
                out.println("<script>window.location='index.html';</script>");
            }
        } catch (SQLException error) {
            out.print(error.toString());
        }
    %>
</body>
</html>
