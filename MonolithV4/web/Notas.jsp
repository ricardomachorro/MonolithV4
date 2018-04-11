

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
        <title>Notas</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/NotasCSS2.css" rel="stylesheet" type="text/css">
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
                        <a class="nav-link"  href="Logros.jsp"><img src="img/icon.svg" class="ImagenesBarraInicio" >Logros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Ayuda.jsp"><img src="img/support.svg" class="ImagenesBarraInicio" >Ayuda</a>
                    </li>
                </ul>
                <ul class="navbar-nav mr-left mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <img src="img/user.svg" class="ImagenesBarraInicio" > Usuario
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
                    <div class="card-deck"  >
                        <div class="card IngresoNota">
                            <h2 class="TituloNota" id="0">
                                Titulo Nota 
                            </h2>
                            <form id="IngresoNotaForm">
                                <div class="row">

                                    <div class="col-lg-6 col-md-12 col-sm-12">
                                        <input type="text" id="txtNuevaNota" name="txtNuevaNota" class="form-control" placeholder="Nueva Nota" >
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <button class="btn-primary" id="BotonNuevaNota" >Agregar nota<img src="img/add-square-button.svg" ></button>  
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <button class="btn-primary" id="BotonActNota" >Actualizar nota<img src="img/add-square-button.svg" ></button>  
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card-deck"  >
                        <div class="card ContenidoNota">
                            <textarea id="txtContenidoNota" class="text" name="message" rows="6" cols="6">
                            
                            </textarea>
                        </div>
                    </div>   
                </div>
                <!--Fin Contenedor Principal-->
                <!--Contenedor Lateral-->
                <div class="col-lg-4 col-md-12 col-sm-12">
                    <div class="card-deck">
                        <div class="card ContenedoresLaterales"  >
                            <div class="card-body" >
                                <div class="row">
                                    <div class="col-sm-6 col-md-8 col-lg-8">
                                        <h1>Notas</h1>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4">
                                        <img class="float-right" src="img/post-it.svg">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card-deck" >
                        <div class="card ListasLaterales" style="height:850px;"  >
                            <div class="card-body" >
                                <ul id="ListaNotas">
                                    <%
                                        Statement st2 = conexion.createStatement();
                                        ResultSet rs2 = st2.executeQuery("select * from Nota inner join Usuario on Usuario.IDUsuario=Nota.IDUsuario where Usuario.NombreUsuario='" + Usuario + "'");
                                        while (rs2.next()) {
                                            out.println(" <li class='NotasListas' id='" + rs2.getString("Nombre") + "'><img src='img/post-it.svg'>" + rs2.getString("Nombre") + "</li>");
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
    </body>
    <script>
        $("#BotonNuevaNota").click(function () {
            $("#IngresoNotaForm").validate({
                rules: {
                    txtNuevaNota: {
                        required: true
                    }
                }, messages: {
                    txtNuevaNota: {
                        required: "Llene el Campo "
                    }
                }, submitHandler: function (form) {
                    var TituloNota = $("#txtNuevaNota").val();
                    var Contenido = $("#txtContenidoNota").val();
                    var NotasRepetidas = $("#ListaNotas").find("#" + TituloNota).length;
                    if (NotasRepetidas === 0) {
                        $.ajax({
                            url: "GuardarNota",
                            type: 'post',
                            data: {
                                TitleNota: TituloNota,
                                ContenidoNota: Contenido
                            }, success: function () {
                                $("#ListaNotas").prepend("<li class='NotasListas' id='" + TituloNota + "'><img src='img/post-it.svg'>" + TituloNota + "</li>");
                            }, error: {

                            }

                        });
                    } else {
                        alert("Nota repetida");
                    }



                }
            });
        });

        $(".NotasListas").click(function () {
            var ElementoSeleccionado = $(this).attr("id");
            $.ajax({
                url: "PonerNota",
                type: 'post',
                data: {
                   NombreElemento:ElementoSeleccionado
                }, success: function (data) {
                    alert(data);
                    $(".TituloNota").text(ElementoSeleccionado);
                    $("#txtContenidoNota").val(data);
                }, error: {

                }
            });


        });
        
        $("#BotonActNota").click(function(){
            $("#IngresoNotaForm").validate({
                rules: {
                    txtNuevaNota: {
                        required: true
                    }
                }, messages: {
                    txtNuevaNota: {
                        required: "Llene el Campo "
                    }
                }, submitHandler: function (form) {
                   var TituloNota = $("#txtNuevaNota").val();
                   var Contenido = $("#txtContenidoNota").val();
                   var TituloAnterior=$(".TituloNota").val();
                        $.ajax({
                            url: "ActualizarNota",
                            type: 'post',
                            data: {
                                TitleNota: TituloNota,
                                ContenidoNota: Contenido,
                                Anterior:TituloAnterior
                            }, success: function () {
                            
                            }, error: {

                            }

                        });
                }
            });
        });



    </script>
</html>
