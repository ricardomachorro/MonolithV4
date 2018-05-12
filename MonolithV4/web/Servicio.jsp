<%-- 
    Document   : Servicio
    Created on : 08-abr-2018, 11:21:37
    Author     : Ricardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%
    HttpSession sesion = request.getSession();
    String Usuario = sesion.getAttribute("usuario").toString();
    String Password = sesion.getAttribute("password").toString();
    Connection conexion = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost/MonolithV2";
    String usuario = "root";
    String clave = "n0m3l0";
    try {
        Class.forName(driver);
        conexion = DriverManager.getConnection(url, usuario, clave);

        /*guardando la conexion en la session*/
        session.setAttribute("conexion", conexion);
    } catch (Exception ex) {

    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicio</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/ServicioCSS.css" rel="stylesheet" type="text/css">
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

                <ul class="navbar-nav mr-left mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <img src="img/user.svg" class="ImagenesBarraInicio" > <%out.println(Usuario);%>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink" style="align-content:right;">
                            <a class="dropdown-item" id="UsuarioName" >Usuario: <%                                out.println(Usuario);
                                %></a>
                            <a class="dropdown-item" href="CerrarSesion.jsp"><img src="img/enter.svg" class="ImagenesBarraInicio" > Cerrar Sesion</a>
                            <a class="dropdown-item" href="Configuracion.jsp"><img src="img/settings-work-tool.svg" class="ImagenesBarraInicio" >
                                Configuracion</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row ContenedorModulo">
                <div class="col-sm-12 col-md-4 col-lg-4 ElementosModulo ">
                    <nav class="navbar  navbar-light bg-light">
                        <a class="navbar-brand" href="#">
                            <img src="img/question-mark.svg" id="IconoChat" class="d-inline-block align-top" alt="">
                           Clientes con Duda
                        </a>
                    </nav>
                    <div class="row BarraClientes">
                        <div class="col-12 " >
                            <div class="card-deck carta">
                                <div class="card">
                                    <h3 class="card-title" >Usuario 1</h3>
                                    <div class="card-body" >
                                        <div class="row">
                                            <div class="col-4">
                                                <img class="imgUser" src="img/user.svg" > 
                                            </div>
                                            <div class="col-8">
                                               saaaaaaaaaaaaaaaaaaaaaaaaaaaa
                                               saaaaaaaaaaaaaaaa
                                               asssssssssssssssssssss
                                               assasaaaaaaaaaaaaa
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="card-deck carta">
                                <div class="card">
                                    <h3 class="card-title" >Usuario 1</h3>
                                    <div class="card-body" >
                                        <div class="row">
                                            <div class="col-4">
                                                <img class="imgUser" src="img/user.svg" > 
                                            </div>
                                            <div class="col-8">
                                               saaaaaaaaaaaaaaaaaaaaaaaaaaaa
                                               saaaaaaaaaaaaaaaa
                                               asssssssssssssssssssss
                                               assasaaaaaaaaaaaaa
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="card-deck carta">
                                <div class="card">
                                    <h3 class="card-title" >Usuario 1</h3>
                                    <div class="card-body" >
                                        <div class="row">
                                            <div class="col-4">
                                                <img class="imgUser" src="img/user.svg" > 
                                            </div>
                                            <div class="col-8">
                                               saaaaaaaaaaaaaaaaaaaaaaaaaaaa
                                               saaaaaaaaaaaaaaaa
                                               asssssssssssssssssssss
                                               assasaaaaaaaaaaaaa
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-8 col-lg-8  ElementosModulo">
                    <nav class="navbar  navbar-light bg-light">
                        <a class="navbar-brand" href="#">
                            <img src="img/speech-bubble.svg" id="IconoChat" class="d-inline-block align-top" alt="">
                            Cliente:Cliente
                        </a>
                    </nav>
                    <div class="card-deck" style="margin:12px;">
                        <div class="card">
                            <div class="row">
                                <div class="col-12" id="SeccionMensaje" style="height:700px;background-color:#fff;overflow-y:scroll;">
                                    <div class="clearfix Mensaje"><blockquote class="me float-left">Hi</blockquote></div>
                                    <div class="clearfix Mensaje"><blockquote class="you float-right">Hello</blockquote></div>
                                    <div class="clearfix Mensaje"><blockquote class="you float-right">Hello</blockquote></div>
                                    <div class="clearfix Mensaje"><blockquote class="me float-left">Hi</blockquote></div>
                                    <div class="clearfix Mensaje"><blockquote class="me float-left">Hi</blockquote></div>
                                    <div class="clearfix Mensaje"><blockquote class="me float-left">Hi</blockquote></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card-deck" style="margin:12px;">
                        <div class="card">

                            <div class="row" style="padding:12px;">
                                <div class="col-lg-10 col-md-6 col-sm-12">
                                    <input type="text" id="Texto"  style="width:100%;"  class="form-control" placeholder="Mensaje" >
                                </div>
                                <div class="col-lg-2 col-md-6 col-sm-12">
                                    <button class="btn-primary" style="width:100%;;height:40px" onclick="sendMessage();"><img src="img/sent-mail.svg" style="width:30px;height:30px;margin-right:17px;">Enviar</button>  
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </body>
</html>
