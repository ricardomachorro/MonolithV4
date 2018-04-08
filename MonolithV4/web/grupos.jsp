<%-- 
    Document   : grupos
    Created on : 3/04/2018, 10:04:40 PM
    Author     : Hawkward
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            //Variables de sesion
            HttpSession sesion = request.getSession();
            String nomUsuario = sesion.getAttribute("usuario").toString();

            //Variables de conexion a BD
            Connection con = null;
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/MonolithV2";
            String usr = "root";
            String clave = "n0m3l0";

            //Conexion a BD
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, usr, clave);
                //Guardando la conexion en la session
                session.setAttribute("conexion", con);
            } catch (Exception error) {
                System.out.println("Error: " + error + " :'v");
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Grupos</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="Css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="Css/BarraDeInicioSesion.css">
        <link rel="stylesheet" type="text/css" href="Css/Grupos.css">
    </head>
    <body>
        <!--Inicio barra de navegacion-->
        <nav class="navbar navbar-expand-lg navbar-light  BarraDeInicio">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="true" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand"><img src="img/pawn.svg" id="LogoBarraInicio"></a>

            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">

                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="Actividades.jsp"><img src="img/signing-the-contract.svg" class="ImagenesBarraInicio" >Actividades</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="grupos.html"><img src="img/group.svg" class="ImagenesBarraInicio " >Grupos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Notas.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Notas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Logros.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Logros</a>
                    </li>
                </ul>

                <ul class="navbar-nav mr-left mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <img src="img/user.svg" class="ImagenesBarraInicio" > 
                            <%out.println(nomUsuario);%>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink" style="align-content:center;">
                            <a class="dropdown-item" id="UsuarioName" >Usuario: <%out.println(nomUsuario);%></a>
                            <a class="dropdown-item" href="#CerrarSesion"><img src="img/enter.svg" class="ImagenesBarraInicio" > Cerrar Sesion</a>
                            <a class="dropdown-item" href="#Configuracion"><img src="img/settings-work-tool.svg" class="ImagenesBarraInicio" >
                                Configuracion</a>
                        </div>
                    </li>
                </ul>

            </div>
        </nav>
        <!--Fin barra de navegacion-->

        <!--Inicio contenedor-->
        <div class="container-fluid">
            <div class="row">
                <!-- Lado derecho - Contenedor principal -->
                <div class="col-lg-8 col-md-8 col-sm-12">
                    <div class="tab-content" id="nav-tabContent">
                        <%
                            String query = "select * from Grupo inner join Usuario on Grupo.UsuarioLider=Usuario.IDUsuario where Usuario.NombreUsuario='" + nomUsuario + "'";
                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery(query);

                            String nombreGrupo;
                            while (rs.next()) {
                                nombreGrupo = (String) rs.getString("NombreGrupo");
                        %>
                        <!--Inicio de un grupo-->
                        <div class='tab-pane fade' <%out.println("id='panel-g" + nombreGrupo + "' role='tabpanel' aria-labelledby='lista-g" + nombreGrupo + "'");%>>

                            <!--Inicio titulo contenedor-->
                            <div class='card-deck'>
                                <div class='card Contenedor'>
                                    <div class='card-body'>
                                        <div class='row'>
                                            <div class='col d-flex align-items-center justify-content-center'>
                                                <%out.println("<h2>" + nombreGrupo + "</h2>");%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--Fin titulo contenedor-->
                            <!--Inicio cuerpo contenedor-->
                            <div class='card-deck'>
                                <div class='card'>
                                    <div class='card-body'>
                                        <!--Inicio agregar tarea-->
                                        <form>
                                            <div class='form-row align-items-center'>
                                                <div class='col-sm-5 mt-2'>
                                                    <input type='text' class='form-control' placeholder='Ingresa una tarea'>
                                                </div>
                                                <div class='col-sm-5 mt-2'>
                                                    <div class='input-group'>
                                                        <div class='input-group-prepend'>
                                                            <div class='input-group-text'>@</div>
                                                        </div>
                                                        <input type='text' class='form-control' placeholder='Correo'>
                                                    </div>
                                                </div>
                                                <div class='col-sm-2 d-flex justify-content-center mt-2'>
                                                    <button type='submit' class='btn btn-primary'>Agregar</button>
                                                </div>
                                            </div>
                                        </form>
                                        <!--Fin agregar tarea-->
                                        <!--Inicio contenedor de lista de tareas-->
                                        <div class='row rowListaTareas'>
                                            <!--Inicio lista de tareas (IMPORTANTE CAMBIAR ID'S por grupo)-->
                                            <div class='col-12' <%out.println("id='listaTareas-" + nombreGrupo + "'");%>>
                                                <%
                                                    //Generador de actividades
                                                %>
                                            </div>
                                            <!--Fin lista de tareas-->
                                        </div>
                                        <!--Fin de contenedor de lista de tareas-->
                                    </div>
                                </div>
                            </div>
                            <!--Fin cuerpo contenedor-->
                        </div>
                        <!--Fin de un grupo-->
                        <%
                            };
                        %>       
                    </div>
                </div>
                <!--Fin lado derecho-->

                <!-- Lado izquierdo - Contenedor lateral -->
                <div class="col-lg-4 col-md-4 col-sm-12">
                    <!--Inicio titulo contenedor-->
                    <div class="card-deck">
                        <div class="card Contenedor">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-6 col-md-8 col-lg-8 d-flex align-items-center justify-content-center">
                                        <h2>Tus grupos</h2>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4 d-flex align-items-center justify-content-center">
                                        <img class="float-right" src="img/group.svg">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Fin titulo contenedor-->
                    <!--Inicio cuerpo contenedor-->
                    <div class="card-deck">
                        <!--Inicio carta-->
                        <div class="card ListasLaterales">
                            <!--Inicio lista de grupos-->
                            <div class="list-group" id="list-tab" role="tablist">
                                <!--Aqui van los grupos listados (Inicio)-->
                                <div id="lista-grupos">
                                    <!--
                                    <a class="list-group-item list-group-item-action" id="lista-gNombreGrupo" data-toggle="list" href="#panel-gNombreGrupo" role="tab" aria-controls="gNombreGrupo">
                                        <img src="img/group.svg" alt="ic_grupos">
                                            Nombre grupo
                                    </a>
                                    -->
                                </div>
                                <!--Grupos listados (Final)-->
                                <!--BTN PARA COLAPSAR-->
                                <a class="list-group-item list-group-item-action btn" data-toggle="collapse" href="#agregarGrupo" 
                                   role="button" aria-expanded="false" aria-controls="collapseExample">
                                    Agregar grupo
                                </a>
                                <!--Inicio contenido colapsable-->
                                <div class="collapse" id="agregarGrupo">
                                    <!--Inicio agregar grupo-->
                                    <a class="list-group-item">
                                        <div class="d-flex flex-column align-items-center">
                                            <div>
                                                <h5>Nuevo grupo</h5>
                                            </div>
                                            <div>
                                                <form>
                                                    <div class="form-row">
                                                        <div class="form-group col-12">
                                                            <input type="text" class="form-control" placeholder="Nombre grupo">
                                                        </div>
                                                        <div class="col-9">
                                                            <input type="email" class="form-control" placeholder="Correo del integrante">
                                                        </div>
                                                        <div class="col-3">
                                                            <button class="form-control">Agregar</button>
                                                        </div>
                                                        <div class="form-group col-12">
                                                            <ul class="form-control list-group agregarMiembro">
                                                                <!--
                                                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                                                    Nombre miembro
                                                                    <span class="badge">
                                                                        <button type="button" class="close" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                    </span>
                                                                </li>
                                                                -->
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <button type="submit" class="btn btn-primary form-control">Guardar</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </a>
                                    <!--Fin agregar grupo-->
                                </div>
                                <!--Fin contenido colapsable-->
                            </div>
                            <!--Fin lista de grupos-->
                        </div>
                        <!--Fin carta-->
                    </div>
                    <!--Fin cuerpo contenedor-->
                </div>
            </div>
            <!--Fin lado izquierdo-->
        </div>
        <!--Fin contenedor-->

        <!--SCRIPTS-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>

        <script>

        </script>
    </body>
</html>
