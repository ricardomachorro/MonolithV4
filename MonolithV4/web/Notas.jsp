<%-- 
    Document   : Notas
    Created on : 25-feb-2018, 18:12:09
    Author     : Ricardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notas</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/NotasCSS.css" rel="stylesheet" type="text/css">
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
        <div class="container-fluid">
            <div class="row" >

                    <div class="col-lg-8 col-md-8 col-sm-12">
                        <div class="row BandejaNotas">
                            <div class="col-12 ">
                                <div class="row">
                                    <div class="col-12 TituloContenedores">
                                        <div class="row">
                                            <div class="col-8" style="padding-top:40px;">
                                                <a>Agregar notas</a>
                                            </div> 
                                            <div class="col-4">
                                                
                                                <form method="post" action="agregarnot.jsp"  >
                                                
                                                <button type="submit" class="btn btn-outline-danger  float-right">Agregar</button>
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 SeparadorTituloContendores">

                                    </div>
                                    hola
                                    <div>
                                        <textarea placeholder="Escribe la notas" id="nota" name="nota"> </textarea>
                                    </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                

                <div class="col-lg-4 col-md-4 col-sm-12">
                    <div class="row BandejaLateral" >
                        <div class="col-12 ">
                            <div class="row">
                                <div class="col-12 TituloLateralesContenedores">
                                    <div class="row">
                                        <div class="col-8" style="padding-top:40px;">
                                            <a>Notas</a>
                                        </div> 
                                        <div class="col-4">
                                            <img class="float-right" src="img/folderOrange.svg" ><!--E3A229-->
                                        </div> 
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 SeparadorTituloContendoresLateral">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12" style="height:650px;background-color:#fff;overflow-y:scroll;">
                                    <button class="btn btn-secondary" data-toggle="modal" data-target="#fm-modal" > Insertar nota</button>


                                    <div class="modal fade" id="fm-modal" tabindex="-1" role="dialog" aria-labelledby="fm-modal" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="">Nueva nota</h5>
                                                    <button class="close" data-dismiss="modal" aria-label="Cerrar">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>

                                                <div class="modal-body">
                                                    <form method="post" action="agregarnot.jsp">
                                                        <input type="text" placeholder="Ingresa el nombre de la nota" size="50"  id="nombre" name="nombre" required/><br><br>
                                                        <input type="text" placeholder="Ingresa la descripcion de la nota" size="50" id="nota" name="nota"required/><br><br>

                                                        <input type="submit" value="Ingresar" class="btn btn-secondary">
                                                    </form>

                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div> 

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
