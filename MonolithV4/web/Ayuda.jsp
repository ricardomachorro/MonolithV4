

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
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/AyudaCSS.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <link href="Css/chat.css" rel="stylesheet" type="text/css"/>
        <title>Ayuda</title>
    </head>
    <script>
        var webSocket = new WebSocket("ws://localhost:8080/MonolithV4/WebSocket1");
        webSocket.onmessage = function processMessage(message) {
            var jsonData = message.data;

            mensaje.value += jsonData.message + "\n";

        }
        function sendMessage() {
            webSocket.send(Texto.value);
            Texto.value = "";
        }
    </script>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light  BarraDeInicio">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="true" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand"><img src="img/pawn.svg" id="LogoBarraInicio"></a>

            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link"  href="Actividades.jsp"><img src="img/signing-the-contract.svg" class="ImagenesBarraInicio">Actividades</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="grupos.jsp"><img src="img/group.svg" class="ImagenesBarraInicio">Grupos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Notas.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio">Notas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="LogrosPrue.jsp"><img src="img/icon.svg" class="ImagenesBarraInicio">Logros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Ayuda.jsp"><img src="img/support.svg" class="ImagenesBarraInicio">Ayuda</a>
                    </li>

                </ul>
                <ul class="navbar-nav mr-left mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <img src="img/user.svg" class="ImagenesBarraInicio" > <%out.println(Usuario);%>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink" style="align-content:center;">
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
                        <a class="navbar-brand" >
                            <img src="img/question-mark.svg" id="IconoChat" class="d-inline-block align-top" alt="">
                            ¿Tienes Problemas?
                        </a>
                    </nav>

                    <div class="card-deck">
                        <div class="card">
                            <div class="card-body">
                                <p class="card-text">Si tiene alguna duda con el sistema, o problematica con este, en esta parte del 
                                    sistema se le provee a los usuarios un chat con los elementos de Soporte de Software, para responder 
                                    cualquiera de las dudas que podria tener
                                </p>


                            </div>
                        </div>
                    </div>


                    <nav class="navbar  navbar-light bg-light" style="margin-top:30px;">
                        <a class="navbar-brand" >
                            <img src="img/question-mark.svg" id="IconoChat" class="d-inline-block align-top" alt="">
                            ¿Si no funciona?
                        </a>
                    </nav>

                    <div class="card-deck">
                        <div class="card">
                            <div class="card-body">
                                <p class="card-text">
                                    En el caso de no funcionar el cat proporcionado se tnedran los siguientes formas de
                                    contacto con el equipo de desarrollo
                                </p>
                                <ul>
                                    <li>Telefono:53 47 08 95</li>
                                    <li>Correo:hawkward.ipn@gmail.com</li>
                                </ul>

                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-sm-12 col-md-8 col-lg-8  ElementosModulo">
                    <nav class="navbar  navbar-light bg-light">
                        <a class="navbar-brand" href="#">
                            <img src="img/speech-bubble.svg" id="IconoChat" class="d-inline-block align-top" alt="">
                            Chat
                        </a>
                    </nav>
                    <div class="col-md-12 chat-box ">
                        <div class="panel panel-primary">
                            <div class="panel-heading">

                            </div>

                            <div class="panel-body chat-widget" style="background-color: #FFF">
                                <ul class="chat chat-messages">                




                                </ul>
                            </div>

                            <div class="panel-footer">
                                <div class="form-group">
                                    <input type="text" class="form-control input-sm chat-name" placeholder="Escriba su nombre"value="<%=Usuario%>" readonly="readonly">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control input-sm chat-entry" placeholder="Escriba un mensaje y presione enter para enviar">
                                </div>
                            </div>

                        </div>
                    </div>


                </div>
            </div>
        </div>
        <script src="https://js.pusher.com/4.1/pusher.min.js"></script>
        <script src="js/chat.js" type="text/javascript"></script>
        <script>

        // Enable pusher logging - don't include this in production
        Pusher.logToConsole = true;

        var pusher = new Pusher('97e8c5b3c583140e8b63', {
            cluster: 'us2',
            encrypted: true
        });
        var chat = new ChatWidget(pusher);

        </script>
    </body>
</html>
