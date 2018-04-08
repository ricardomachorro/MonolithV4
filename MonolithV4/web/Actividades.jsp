<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%/*
    HttpSession sesion = request.getSession();
    String Usuario = sesion.getAttribute("usuario").toString();
    String Password = sesion.getAttribute("password").toString();
     */
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actividades</title>
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
                        <a class="nav-link"  href="Notas.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Notas</a>
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
        <div class="container-fluid">
            <div class="row">
                <!--Contenedor Principal-->
                <div class="col-lg-8 col-md-12 col-sm-12">
                    <!--Contenedro de Resumen-->
                    <div class="card-deck">
                        <div class="card CartasResumenActividades" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-4">
                                        <img src="img/star.svg"  class="float-left ImagenesResumenActividades" >
                                    </div>
                                    <div class="col-8 TextoCartasResumenActividades" >
                                        <a >Actividades finalizadas</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card CartasResumenActividades" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-4">
                                        <img src="img/warning.svg"  class="float-left ImagenesResumenActividades" >
                                    </div>
                                    <div class="col-8 TextoCartasResumenActividades" >
                                        <a >Actividades sin finalizar</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card CartasResumenActividades" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-4">
                                        <img src="img/help-button-speech-bubble-with-question-mark.svg"  class="float-left ImagenesResumenActividades" >
                                    </div>
                                    <div class="col-8 TextoCartasResumenActividades" >
                                        <a >Actividades sin especificar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Fin Contenedor de Resumen-->
                    <!--Bandeja Actividad Principal-->

                    <!--Titulo Bandeja-->
                    <div class="card-deck">
                        <div class="card" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-6 col-md-8 col-lg-8">
                                        <h1>Bandeja Principal de Actividades</h1>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4">
                                        <img id="ImagenBandejaPrincipal" class="float-right" src="img/copy.svg">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Fin Titulo Bandeja-->

                    <div class="card-deck">
                        <div class="card" >
                            <div class="card-body">
                                <!--Seccion Nueva Actividad-->

                                <div class="row SeccionNuevaActividad" >
                                    <div class="col-lg-8 col-md-6 col-sm-12">
                                        <input type="text" id="NuevaActividadtxt" class="form-control" placeholder="Nueva Actividad" >
                                    </div>
                                    <div class="col-lg-4 col-md-6 col-sm-12">
                                        <button class="btn-primary" type="submit" id="NuevaActividadBtn" >Agregar Actividad<img src="img/add-square-button.svg" ></button>  
                                    </div>
                                </div>

                                <!--Fin Seccion Nueva Actividad-->


                                <!--Bandeja Actividades-->
                                <div class="row BandejaActividades">
                                    <div class="col-12">
                                        <!--Actividad Abierta-->
                                        <div class="card-deck">
                                            <div class="card ActividadCarta ActividadActiva" >
                                                <div class="card-body" data-toggle="collapse" href="#Col1">
                                                    <div class="row " >
                                                        <div class="col-10">
                                                            <h5>Nombre Actividad:Actividad1     Fecha:16/03/2018   Localización:dsfdffds</h5>
                                                        </div>
                                                        <div class="col-2">
                                                            <input class="CheckBoxActividades float-right" type="checkbox">
                                                        </div>
                                                    </div>

                                                    <div class="collapse row OpccionesAcividad" id="Col1" >
                                                        <form class="form-inline">
                                                            <input class="form-control"  type="text" placeholder="Nombre Activdad">
                                                            <input class="form-control"  type="text" placeholder="Fecha Activdad">
                                                            <input class="form-control" type="text" placeholder="Categoria">
                                                            <div class="btn-group btn-group-sm  btn-activity" role="group" >
                                                                <button type="button" class="btn btn-secondary"><img src="img/save.svg"></button>
                                                                <button type="button" class="btn btn-secondary"> <img src="img/garbageWhite.svg"></button>
                                                                <button type="button" class="btn btn-secondary"><img src="img/placeholderWhite.svg"></button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--Fin Actividad Abierta-->
                                        <!--Actividad Normal 1-->
                                        <div class="card-deck">
                                            <div class="card ActividadCarta ActividadActiva" >
                                                <div class="card-body" data-toggle="collapse" href="#Col2">
                                                    <div class="row " >
                                                        <div class="col-10">
                                                            <h5>Nombre Actividad:Actividad1     Fecha:16/03/2018   Localización:dsfdffds</h5>
                                                        </div>
                                                        <div class="col-2">
                                                            <input class="CheckBoxActividades float-right" type="checkbox">
                                                        </div>
                                                    </div>

                                                    <div class="collapse row OpccionesAcividad" id="Col2" >
                                                        <form class="form-inline">
                                                            <input class="form-control"  type="text" placeholder="Nombre Activdad">
                                                            <input class="form-control"  type="text" placeholder="Fecha Activdad">
                                                            <input class="form-control" type="text" placeholder="Categoria">
                                                            <div class="btn-group btn-group-sm  btn-activity" role="group" >
                                                                <button type="button" class="btn btn-secondary"><img src="img/save.svg"></button>
                                                                <button type="button" class="btn btn-secondary"> <img src="img/garbageWhite.svg"></button>
                                                                <button type="button" class="btn btn-secondary"><img src="img/placeholderWhite.svg"></button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--Fin Actividad Abierta 1-->
                                        <div class="card-deck">
                                            <div class="card ActividadCarta" >

                                                <div class="card-body">
                                                    <div class="row " >
                                                        <div class="col-10">
                                                            <h5>Nombre Actividad:Actividad1     Fecha:16/03/2018   Localización:dsfdffds</h5>
                                                        </div>
                                                        <div class="col-2">
                                                            <input class="CheckBoxActividades float-right" type="checkbox">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="card-deck">
                                            <div class="card ActividadCarta" >

                                                <div class="card-body">
                                                    <div class="row " >
                                                        <div class="col-10">
                                                            <h5>Nombre Actividad:Actividad1     Fecha:16/03/2018   Localización:dsfdffds</h5>
                                                        </div>
                                                        <div class="col-2">
                                                            <input class="CheckBoxActividades float-right" type="checkbox">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--Fin Bandeja Actividades-->
                            </div>
                        </div>
                    </div>
                    <!--Fin Bandeja Actividad Principal-->
                </div>
                <!--Fin Contenedor Principal-->
                <!--Contenedor Lateral-->
                <div class="col-lg-4 col-md-12 col-sm-12">
                    <div class="card-deck">
                        <div class="card ContenedoresLaterales"  >
                            <div class="card-body" >
                                <div class="row">
                                    <div class="col-sm-6 col-md-8 col-lg-8">
                                        <h1>Categorias</h1>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4">
                                        <img class="float-right" src="img/folderOrange.svg">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-deck">
                        <div class="card ListasLaterales"  >
                            <div class="card-body" >
                                <ul>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>
                                    <li><img src="img/folderOrange.svg">asssa</li>

                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="card-deck">
                        <div class="card ContenedoresLaterales"  >
                            <div class="card-body" >
                                <div class="row">
                                    <div class="col-sm-6 col-md-8 col-lg-8">
                                        <h1>Localizaciones</h1>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4">
                                        <img class="float-right" src="img/placeholder.svg">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-deck">
                        <div class="card ListasLaterales"  >
                            <div class="card-body" >
                                <ul>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>
                                    <li><img src="img/placeholder.svg">asssa</li>    
                                </ul>
                            </div>
                        </div>
                    </div>




                </div>
                <!--Fin Contenedor Lateral-->


            </div>
        </div>
        <script>

        </script>

        <script>

            $("#NuevaActividadBtn").click(function () {
                var NombreActividad = $("#NuevaActividadtxt").val();
                var Actividad = {
                    Nombre: NombreActividad,
                    Clase: "Todos",
                    Usuario: "rick1234"
                };
                $.ajax({
                    type: "POST",
                    url: "IngresarActividad",
                    contentType: "application/json", // NOT dataType!
                    data: JSON.stringify(Actividad)

                });
            });




        </script>
    </body>
</html>
