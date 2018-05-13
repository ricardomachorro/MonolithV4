<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="Seguridad.*" %>
<!DOCTYPE html>
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
                        <a class="nav-link"  href="grupos.jsp"><img src="img/group.svg" class="ImagenesBarraInicio">Grupos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Notas.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio">Notas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="LogrosPrue.jsp"><img src="img/icon.svg" class="ImagenesBarraInicio">Logros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Ayuda.jsp"><img src="img/support.svg" class="ImagenesBarraInicio" >Ayuda</a>
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
                                        <%
                                            Statement stcons2 = conexion.createStatement();
                                            String UsuarioBusqueda1 = sesion.getAttribute("usuario").toString();
                                            ResultSet rscons2 = stcons2.executeQuery("select * from Actividad inner join Categoria on Actividad.IDCategoria=Categoria.IDCategoria "
                                                    + "inner join Usuario on Categoria.IDUsuario=Usuario.IDUsuario where Usuario.NombreUsuario='" + UsuarioBusqueda1 + "' and Actividad.Estado>0");
                                            if (rscons2.next()) {
                                                if (rscons2.last()) {
                                                    out.println("<a id='txtActividadesFinalizadas' >Actividades finalizadas: " + rscons2.getRow() + "</a>");
                                                }
                                            } else {
                                                out.println("<a id='txtActividadesFinalizadas' >Actividades finalizadas: 0</a>");
                                            }
                                        %>

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
                                        <%
                                            Statement stcons = conexion.createStatement();
                                            String UsuarioBusqueda2 = sesion.getAttribute("usuario").toString();
                                            ResultSet rscons = stcons.executeQuery("select * from Actividad inner join Categoria on Actividad.IDCategoria=Categoria.IDCategoria "
                                                    + "inner join Usuario on Categoria.IDUsuario=Usuario.IDUsuario where Usuario.NombreUsuario='" + UsuarioBusqueda2 + "' and Actividad.Estado=0");
                                            if (rscons.next()) {
                                                if (rscons.last()) {
                                                    out.println("<a id='ActividadesNoFinalizadas' >Actividades no finalizadas: " + rscons.getRow() + "</a>");
                                                }
                                            } else {
                                                out.println("<a id='ActividadesNoFinalizadas'>Actividades no finalizadas: 0</a>");
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--
                        <div class="card CartasResumenActividades" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-4">
                                        <img src="img/help-button-speech-bubble-with-question-mark.svg"  class="float-left ImagenesResumenActividades" >
                                    </div>
                                    <div class="col-8 TextoCartasResumenActividades" >
                                        <a  id="TxtActividadesSinEspecificar">Actividades sin especificar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        -->
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
                                <!--method="Post" action="IngresarActividad"-->
                                <form id="FormuNuevaActividad">
                                    <div class="row SeccionNuevaActividad" >
                                        <div class="col-lg-8 col-md-6 col-sm-12">
                                            <input type="text" id="NuevaActividadtxt" name="NuevaActividadtxt" class="form-control" placeholder="Nueva Actividad" >
                                        </div>
                                        <div class="col-lg-4 col-md-6 col-sm-12">
                                            <button class="btn-primary" id="NuevaActividadBtn" >Agregar Actividad<img src="img/add-square-button.svg" ></button>  
                                        </div>
                                    </div>
                                </form>
                                <!--Fin Seccion Nueva Actividad-->


                                <!--Bandeja Actividades-->
                                <div class="row BandejaActividades">
                                    <div class="col-12" id="ContenedorCartasActividades">
                                        <!--Actividad Abierta-->

                                        <%
                                            Statement st = conexion.createStatement();
                                            ResultSet rs = st.executeQuery("select * from Actividad inner join Categoria on Actividad.IDCategoria=Categoria.IDCategoria inner join Usuario on Usuario.IDUsuario=Categoria.IDUsuario where Usuario.NombreUsuario='" + Usuario + "'");

                                            while (rs.next()) {
                                                out.println("<div class='card-deck ActividadesCard " + rs.getString("NombreCategoria") + "'>");
                                                out.println(" <div class='card ActividadCarta ActividadActiva' id='" + rs.getInt("IDActividad") + "' >");
                                                out.println("<div class='card-body'>");
                                                out.println("<div class='row '  >");
                                                out.println("<div class='col-10' data-toggle='collapse' href='#Col" + rs.getInt("IDActividad") + "' >");
                                                out.println("<h5 class='ActividadMensaje'>Nombre Actividad: " + rs.getString("Nombre") + "  Fecha:" + rs.getString("Fecha") + " </h5>");
                                                out.println("</div>");
                                                out.println(" <div class='col-2'>");
                                                if (rs.getBoolean("Estado")) {
                                                    out.println(" <input class='CheckBoxActividades float-right' id='" + rs.getInt("IDActividad") + "' checked type='checkbox' >");
                                                } else {
                                                    out.println(" <input class='CheckBoxActividades float-right checar' id='" + rs.getInt("IDActividad") + "'  type='checkbox' >");
                                                }

                                                out.println("</div>");
                                                out.println("</div>");
                                                out.println("<div class='collapse row OpccionesAcividad' id='Col" + rs.getInt("IDActividad") + "' >");
                                                out.println("<form class='form-inline'>");
                                                out.println("<input class='form-control txtNombreActividad' required='true'  type='text' placeholder='Nombre Activdad'>");
                                                out.println("<input class='form-control txtFecha' required='true' type='date' placeholder='Fecha Activdad'>");
                                                out.println("<input class='form-control txtCategoria' required='true' type='text' placeholder='Categoria'>");
                                                out.println("<div class='btn-group btn-group-sm GrupoBotonesActividad float-left' role='group' >");
                                                out.println(" <button type='button' class='btn btn-secondary btn-activity btnSave' id='" + rs.getInt("IDActividad") + "'><img src='img/save.svg'></button>");
                                                out.println("<button type='button' class='btn btn-secondary btn-activity btnDrop'> <img src='img/garbageWhite.svg'></button>");

                                                out.println("</div>");
                                                out.println("</form>");
                                                out.println("</div>");
                                                out.println("</div>");
                                                out.println("</div>");
                                                out.println("</div>");

                                            }
                                        %>


                                        <!-- <div class="card-deck">
                                             <div class="card ActividadCarta ActividadActiva" >
                                                 <div class="card-body" >
                                                     <div class="row "  >
                                                         <div class="col-10" data-toggle="collapse" href="#Col1" >
                                                             <h5>Nombre Actividad:Actividad1     Fecha:16/03/2018   Localización:Pendiente/Nula</h5>
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
                                                             <div class="btn-group btn-group-sm GrupoBotonesActividad float-left" role="group" >
                                                                 <button type="button" class="btn btn-secondary btn-activity"><img src="img/save.svg"></button>
                                                                 <button type="button" class="btn btn-secondary btn-activity"> <img src="img/garbageWhite.svg"></button>
                                                                 <button type="button" class="btn btn-secondary btn-activity"><img src="img/placeholderWhite.svg"></button>
                                                             </div>
                                                         </form>
                                                     </div>
                                                 </div>
                                             </div>
                                         </div>-->
                                        <!--Fin Actividad Abierta-->


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
                        <div class="card"  >
                            <div class="card-body" >
                                <form id="EliminarCategoriaform">
                                    <div class="row SeccionEliminarCategoria" >

                                        <div class="col-lg-8 col-md-6 col-sm-12">
                                            <input type="text" id="EliminarActividadtxt" name="EliminarActividadtxt" class="form-control" placeholder="Categoria a Elimininar" >
                                        </div>
                                        <div class="col-lg-4 col-md-6 col-sm-12">
                                            <button class="btn-primary" id="BtnEliminarCategoria">Eliminar Categoria</button>  
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="card-deck" >
                        <div class="card ListasLaterales" style="height:850px;"  >
                            <div class="card-body" >
                                <ul id="ListaCategorias">
                                    <li class="Todos" id="Todos"><img src='img/folderOrange.svg'>Todos</li>
                                        <%
                                            Statement st2 = conexion.createStatement();
                                            ResultSet rs2 = st.executeQuery("select * from Categoria inner join Usuario on Usuario.IDUsuario=Categoria.IDUsuario where Usuario.NombreUsuario='" + Usuario + "'");
                                            while (rs2.next()) {
                                                out.println(" <li id='" + rs2.getString("NombreCategoria") + "'><img src='img/folderOrange.svg'>" + rs2.getString("NombreCategoria") + "</li>");
                                            }
                                        %>
                                    <!-- <li><img src="img/folderOrange.svg">asssa</li>-->


                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--  
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
                    -->



                </div>
                <!--Fin Contenedor Lateral-->


            </div>
        </div>
        <script>


            $("#NuevaActividadBtn").click(function () {

                $("#FormuNuevaActividad").validate({
                    rules: {
                        NuevaActividadtxt: {
                            required: true
                        }
                    },
                    messages: {
                        NuevaActividadtxt: {
                            required: "Llene el Campo"
                        }
                    },
                    submitHandler: function (form) {
                        var IngresoActividad = $("#NuevaActividadtxt").val();
                        var inidicehash = IngresoActividad.indexOf('#');
                        var NombreActividad = IngresoActividad.substring(0, inidicehash);
                        var NombreCategoria = IngresoActividad.substring(inidicehash, IngresoActividad.length + 1);
                        NombreCategoria = NombreCategoria.replace("#", "");
                        var Fecha = new Date();
                        var FechaString = Fecha.getDate() + "/" + (Fecha.getMonth() + 1) + "/" + Fecha.getFullYear();

                        $.ajax({
                            url: "IngresarActividad",
                            data: {
                                NombreActivity: NombreActividad.toString(),
                                CategoriaActividad: NombreCategoria.toString()

                            },
                            type: 'post',
                            success: function (data) {
                                var ObjetoFecha = new Date();
                                var MesReal = ObjetoFecha.getMonth() + 1;
                                var DiaReal = ObjetoFecha.getDay() + 1;
                                var FechaReal = ObjetoFecha.getFullYear() + "-0" + MesReal + "-0" + DiaReal;
                                $("#ContenedorCartasActividades").prepend($("<div class='card-deck ActividadesCard "   + NombreCategoria + "'><div class='card ActividadCarta ActividadActiva' id='" + data.toString() + "' >" +
                                        "<div class='card-body'>" +
                                        "<div class='row'>" +
                                        "<div class='col-10' data-toggle='collapse' href='#Col"  + data.toString() + "'>" +
                                       "<h5 class='ActividadMensaje'>Nombre Actividad: "  + NombreActividad.toString() + "   Fecha:" + FechaReal + "</h5>" +
                                        "</div>" +
                                        "<div class='col-2'>" +
                                        "<input class='CheckBoxActividades float-right' id='" + data.toString() + "'  checked type='checkbox' >" +
                                        " </div></div>" +
                                        "<div class='collapse row OpccionesAcividad' id='Col" + data.toString() + "' >" +
                                        " <form class='form-inline'>" +
                                        "<input class='form-control txtNombreActividad' required='true' type='text' placeholder='Nombre Activdad'>" +
                                        "<input class='form-control txtFecha' required='true'  type='date' placeholder='Fecha Activdad'>" +
                                        "<input class='form-control txtCategoria' required='true'  type='text' placeholder='Categoria'>" +
                                        "<div class='btn-group btn-group-sm  btn-activity' role='group' >" +
                                        "<button type='button' class='btn btn-secondary btnSave' id='" + data.toString() + "'><img src='img/save.svg'></button>" +
                                        "<button type='button' class='btn btn-secondary btnDrop'> <img src='img/garbageWhite.svg'></button>" +
                                        "</div>" +
                                        "</form>" +
                                        "</div>" +
                                        "</div></div></div></div>"));
                                var ContadorNoFinalizadas = $("input:checkbox:not(:checked)").length;

                                $("#ActividadesNoFinalizadas").text("Actividades no finalizadas: " + ContadorNoFinalizadas);
                                $("#NuevaActividadtxt").val("");

                                if ($("#ListaCategorias").find("#" + NombreCategoria).length === 0) {
                                    $("#ListaCategorias").prepend("<li id='" + NombreCategoria + "'><img src='img/folderOrange.svg'>" + NombreCategoria + "</li>");
                                }

                                $("#ListaCategorias li").click(function () {
                                    var Categoria = $(this).attr("class");
                                    var nombreCategoria = $(this).attr("id");
                                    if (Categoria === "Todos") {
                                        $("#ContenedorCartasActividades").children().show();
                                    } else {
                                        $("#ContenedorCartasActividades").children().not("." + nombreCategoria).hide();
                                        $("#ContenedorCartasActividades").children("." + nombreCategoria).show();
                                    }

                                });

                                $(".CheckBoxActividades").click(function () {
                                    var IDActividad = $(this).attr("id");
                                    $.ajax({
                                        url: "ChequeoActividad",
                                        data: {
                                            Opccion: "1",
                                            IDActividad: IDActividad.toString()
                                        },
                                        type: 'post',
                                        success: function (data) {

                                            $("#txtActividadesFinalizadas").text("Actividades finalizadas: " + data);

                                            $.ajax({
                                                url: "ChequeoActividad",
                                                type: "post",
                                                data: {
                                                    Opccion: "2",
                                                    IDActividad: IDActividad.toString()
                                                },
                                                success: function (data) {
                                                    $("#ActividadesNoFinalizadas").text("Actividades no finalizadas: " + data);

                                                }, error: function (data) {

                                                },
                                                complete: function (data) {

                                                }
                                            });
                                        },
                                        error: function () {

                                        },
                                        complete: function () {

                                        }


                                    });
                                });



                                $(".btnSave").click(function () {

                                    var IDActividad = $(this).attr("id");
                                    var inputNombre = $(this).closest(".OpccionesAcividad").find("input.txtNombreActividad").val();
                                    var inputFecha = $(this).closest(".OpccionesAcividad").find("input.txtFecha").val();
                                    var inputCategoria = $(this).closest(".OpccionesAcividad").find("input.txtCategoria").val();
                                    var mensajeActividad = $(this).closest(".ActividadCarta").find("h5.ActividadMensaje");
                                    $.ajax({
                                        url: "CambiosActividad",
                                        type: 'post',
                                        data: {
                                            IDActivity: IDActividad,
                                            NombreActivity: inputNombre,
                                            FechaActividad: inputFecha,
                                            CategoriaActividad: inputCategoria
                                        },
                                        success: function () {
                                            mensajeActividad.text("Nombre Actividad:" + inputNombre + "  Fecha:" + inputFecha + " Localización:Pendiente/Nula");
                                            if ($("#ListaCategorias").find("#" + inputCategoria).length === 0) {
                                                $("#ListaCategorias").prepend("<li id='" + inputCategoria + "'><img src='img/folderOrange.svg'>" + inputCategoria + "</li>");
                                            }
                                        },
                                        error: {

                                        },
                                        complete: function () {

                                        }

                                    });


                                });

                                $(".btnDrop").click(function () {
                                    var Elemento2 = $(this).closest(".ActividadCart");
                                    var Elemento = $(this).closest(".ActividadCarta");
                                    var IDActividad = $(this).closest(".ActividadCarta").attr("id");
                                    $.ajax({
                                        url: "EliminarActividad",
                                        type: 'post',
                                        data: {
                                            IDActivity: IDActividad
                                        },
                                        success: function () {
                                            Elemento.parent().remove();
                                            Elemento.remove();
                                            var ContadorNoFinalizadas = $("input:checkbox:not(:checked)").length;
                                            var ContadorFinalizadas = $("input:checkbox:checked").length;
                                            $("#txtActividadesFinalizadas").text("Actividades finalizadas: " + ContadorFinalizadas);
                                            $("#txtActividadesNoFinalizadas").text("Actividades no finalizadas:  " + ContadorNoFinalizadas);


                                        },
                                        error: function () {

                                        },
                                        complete: function () {

                                        }
                                    });
                                });

                                $("#BtnEliminarCategoria").click(function () {
                                    var NombreCategoriatext = $("#EliminarActividadtxt").val().toString();
                                    $.ajax({
                                        url: "EliminarCategoria",
                                        type: 'post',
                                        data:
                                                {
                                                    NombreCategoria: NombreCategoriatext
                                                },
                                        error: function () {
                                            alert("Error");
                                        },
                                        success: function () {
                                            $("#" + NombreCategoriatext).remove();
                                            $("#EliminarActividadtxt").val("");
                                            $("." + NombreCategoriatext).remove();
                                        },
                                        complete: function () {

                                        }

                                    });
                                });






                            },
                            error: function () {
                                alert("Error");
                            }, complete: function () {
                                // Handle the complete event



                            }
                        });

                    }
                });




            }
            );

            $(".CheckBoxActividades").click(function () {
                var IDActividad = $(this).attr("id");
                $.ajax({
                    url: "ChequeoActividad",
                    data: {
                        Opccion: "1",
                        IDActividad: IDActividad.toString()
                    },
                    type: 'post',
                    success: function (data) {

                        $("#txtActividadesFinalizadas").text("Actividades finalizadas: " + data);

                        $.ajax({
                            url: "ChequeoActividad",
                            type: "post",
                            data: {
                                Opccion: "2",
                                IDActividad: IDActividad.toString()
                            },
                            success: function (data) {
                                $("#ActividadesNoFinalizadas").text("Actividades no finalizadas: " + data);

                            }, error: function (data) {

                            },
                            complete: function (data) {

                            }
                        });
                    },
                    error: function () {

                    },
                    complete: function () {

                    }


                });
            });


            $(".btnSave").click(function () {

                var IDActividad = $(this).attr("id");
                var inputNombre = $(this).closest(".OpccionesAcividad").find("input.txtNombreActividad").val();
                var inputFecha = $(this).closest(".OpccionesAcividad").find("input.txtFecha").val();
                var inputCategoria = $(this).closest(".OpccionesAcividad").find("input.txtCategoria").val();
                var mensajeActividad = $(this).closest(".ActividadCarta").find("h5.ActividadMensaje");
                $.ajax({
                    url: "CambiosActividad",
                    type: 'post',
                    data: {
                        IDActivity: IDActividad,
                        NombreActivity: inputNombre,
                        FechaActividad: inputFecha,
                        CategoriaActividad: inputCategoria
                    },
                    success: function () {
                        mensajeActividad.text("Nombre Actividad:" + inputNombre + "  Fecha:" + inputFecha + " Localización:Pendiente/Nula");
                        if ($("#ListaCategorias").find("#" + inputCategoria).length === 0) {
                            $("#ListaCategorias").prepend("<li id='" + inputCategoria + "'><img src='img/folderOrange.svg'>" + inputCategoria + "</li>");
                        }
                    },
                    error: {

                    },
                    complete: function () {

                    }

                });


            });


            $(".btnDrop").click(function () {
                var Elemento2 = $(this).closest(".ActividadCart");
                var Elemento = $(this).closest(".ActividadCarta");
                var IDActividad = $(this).closest(".ActividadCarta").attr("id");
                $.ajax({
                    url: "EliminarActividad",
                    type: 'post',
                    data: {
                        IDActivity: IDActividad
                    },
                    success: function () {
                        Elemento.parent().remove();
                        Elemento.remove();
                        var ContadorNoFinalizadas = $("input:checkbox:not(:checked)").length;
                        var ContadorFinalizadas = $("input:checkbox:checked").length;
                        $("#txtActividadesFinalizadas").text("Actividades finalizadas: " + ContadorFinalizadas);
                        $("#txtActividadesNoFinalizadas").text("Actividades no finalizadas:  " + ContadorNoFinalizadas);


                    },
                    error: function () {

                    },
                    complete: function () {

                    }
                });
            });

            $("#ListaCategorias li").click(function () {
                var Categoria = $(this).attr("class");
                var nombreCategoria = $(this).attr("id");
                if (Categoria === "Todos") {
                    $("#ContenedorCartasActividades").children().show();
                } else {
                    $("#ContenedorCartasActividades").children().not("." + nombreCategoria).hide();
                    $("#ContenedorCartasActividades").children("." + nombreCategoria).show();
                }

            });

            $("#BtnEliminarCategoria").click(function () {

                $("#EliminarCategoriaform").validate({
                    rules: {
                        EliminarActividadtxt: {
                            required: true
                        }
                    }, messages: {
                        EliminarActividadtxt: {
                            required: "Llene el campo"
                        }
                    }, submitHandler: function (form) {
                        var NombreCategoriatext = $("#EliminarActividadtxt").val().toString();
                        $.ajax({
                            url: "EliminarCategoria",
                            type: 'post',
                            data:
                                    {
                                        NombreCategoria: NombreCategoriatext
                                    },
                            error: function () {
                                alert("Error");
                            },
                            success: function () {
                                $("#" + NombreCategoriatext).remove();
                                $("#EliminarActividadtxt").val("");
                                $("." + NombreCategoriatext).remove();
                            },
                            complete: function (data) {

                            }

                        });
                    }
                });


            });
        </script>

    </body>
</html>

