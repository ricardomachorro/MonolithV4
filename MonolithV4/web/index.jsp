<%-- 
    Document   : index
    Created on : 27/03/2018, 03:06:48 PM
    Author     : memo0
--%>
<%@page import="BaseDatos.Database2"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>                          
        <meta charset="UTF-8">                                              
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="Css/LoginCSS.css" rel="stylesheet" type="text/css">
        <link href="Css/bootstraplogin.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Mukta" rel="stylesheet">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/Ingreso.js"></script>

    </head>
    <body class="container-fluid">
        <div class="barraInicio navbar navbar-default "  style="font-family:Mukta">Monolith<img  class="logo" src="img/logo.png"></div>
        <!--Primera parte con la imagen de edificios y danlo la bienvenida al sistema  -->
        <div class="pimg1">
            <div class="ptext">
                <span class="border">
                    <!--Mensaje de Bienvenida  -->
                    Bienvenido a Monolith
                </span>
            </div>
        </div>
        <!--Fin de seccion con imagenees del edificio  -->
        <!--Seccion explicando el sistema al usuario  -->
        <section class="section section-light">
            <!--Pregunta introductoria  -->
            <h2>¿Qué es Monolith?</h2>
            <!--Explicación  del sistema -->
            <p>
                Monolith una herramienta 
                para la organización no solo de tus tareas,
                proyectos, finanzas y tiempo si no también 
                para maximizar tu aprovechamiento
            </p>
        </section>
        <!--Fin de seccion explicativa del sistema  -->
        <!--Imagen con registro  -->
        <div class="pimg2">
            <div class="login">
                <div class="loginBox">
                    <form method="Post" action="IngresoPrograma">
                        <img src="img/user.png" class="user">
                        <h2>Ingresa Aqui</h2>
                        <p>Nombre Usuario</p>
                        <input type="text" id="usuario" name="usuario" placeholder="Nombre de Usuario" required>
                        <p>Contraseña</p>
                        <input type="password" id="contrasenia" name="contrasenia" placeholder="Contraseña" required>
                        <div class="row">
                            <div class="col-12" id="MensajeServer">

                            </div>
                        </div>
                        <input type="submit" name="ingresar" value="Ingresar" >
                    </form>
                    <div class="row">
                        <div class="col-3">

                        </div>
                        <div class="col-6">
                            <button id="registro" onclick="location.href = 'recu.jsp'" name="registro" >Olvide mi contraseña</button>
                        </div>
                        <div class="col-2" >

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3">

                        </div>
                        <div class="col-6">
                            <button id="registro" onclick="location.href = 'registro.html'" name="registro" >Registrarse</button>
                        </div>
                        <div class="col-2" >

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
