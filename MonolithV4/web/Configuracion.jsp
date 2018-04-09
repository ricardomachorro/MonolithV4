

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
        <title>Configuracion</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/ConfiguracionCSS.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
    </head>
    <body style="background-color:#e9ecef;">
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
                        <a class="nav-link"  href="Logros.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Logros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href=""><img src="img/post-it.svg" class="ImagenesBarraInicio" >Notas</a>
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

            <div class="card-deck">
                <div class="card" id="ConfiguracionDatos" >
                    <div class="card-body">
                        <h2 class="card-title" id="TituloConfiguraciones">Configuraciones Anteriores</h2>
                        <ul class="list-group list-group-flush" id="ListaConfiguracion">
                            <li class="list-group-item" id="ListaNombre">Nombre Usuario:</li>
                            <li class="list-group-item" id="ListaEdad">Edad:</li>
                            <li class="list-group-item" id="ListaPais">Pais:</li>
                            <li class="list-group-item" id="ListaDireccion">Direccion:</li>
                            <li class="list-group-item" id="ListaCorreo">Correo:</li>
                        </ul>


                    </div>
                </div>

                <div class="card" id="ConfiguracionDatos">
                    <div class="card-body">
                        <h2 class="card-title" >Cambio Configuraciones</h2>
                        
                            <div class="form-group">
                                <label >Nombre Usuario</label>
                                <input id="txtNombreUsuario" class="form-control"  placeholder="Nombre Usuario">
                            </div>
                            <div class="form-group">
                                <label >Edad</label>
                                <input id="txtEdadUsuario" class="form-control"  placeholder="Edad Usuario">
                            </div>
                            <div class="form-group">
                                <label >Pais</label>
                                <input id="txtPaisUsuario" class="form-control"  placeholder="Pais">
                            </div>
                            <div class="form-group">
                                <label >Direccion</label>
                                <input  id="txtDirecUsuario" class="form-control"  placeholder="Direccion">
                            </div>
                            <div class="form-group">
                                <label >Correo</label>
                                <input  id="txtCorreo" class="form-control"  placeholder="Correo">
                            </div>
                            <div class="form-group">
                                <label >Contraseña</label>
                                <input  id="txtContra" class="form-control"  placeholder="Contrsaeña">
                            </div>
                            <div class="form-group">
                                <label >Confirmar Contraseña</label>
                                <input  class="form-control"  placeholder="Confirme Contraseña">
                            </div>
                            <div class="col-auto">
                                <button id="btnCambio" type="submit" class="btn btn-primary mb-2">Submit</button>
                            </div>
                        
                    </div>  
                </div>
            </div>
        </div>
    </div>                   
</body>
</html>
<script>
    $("#btnCambio").click(function(){
        var NombreNuevo=$("#txtNombreUsuario").val();
        var EdadUsuario=$("#txtEdadUsuario").val();
        var PaisUsuario=$("#txtPaisUsuario").val();
        var CorreoUsuario=$("#txtCorreo").val();
        var DirecUsaurio=$("#txtDirecUsuario").val();
        var ContraUsuario=$("#txtContra").val();
        $.ajax({
            url: "ActualizarUsuario",
            type: 'post',
            data:{
                Nombre:NombreNuevo,
                Edad:EdadUsuario,
                Pais:PaisUsuario,
                Direc:DirecUsaurio,
                Correo:ContraUsuario,
                Contra:ContraUsuario
            },
            success:function(){
               $("#ListaNombre").text("Nombre Usuario: "+NombreNuevo);
               $("#ListaEdad").text("Edad: " +EdadUsuario);
               $("#ListaPais").text("Pais: "+PaisUsuario);
               $("#ListaDireccion").text("Direccion: "+DirecUsaurio);
               $("#ListaCorreo").text("Correo: "+CorreoUsuario);
               $("#txtNombreUsuario").val("");
               $("#txtEdadUsuario").val("");
               $("#txtPaisUsuario").val("");
               $("#txtCorreo").val("");
               $("#txtDirecUsuario").val("");
               $("#txtContra").val("");
            },error:{
                
            }
        });
    });
    
</script>

