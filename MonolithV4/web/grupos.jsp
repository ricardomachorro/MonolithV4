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
                response.sendRedirect("Error404.jsp");
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Grupos</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!--<link rel="stylesheet" type="text/Css" href="Css/bootstrap.min.css">-->
        <link rel="stylesheet" type="text/Css" href="Css/BarraDeInicioSesion.css">
        <link rel="stylesheet" type="text/Css" href="Css/Grupos.css">
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
                        <a class="nav-link" href="grupos.html"><img src="img/group.svg" class="ImagenesBarraInicio ">Grupos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Notas.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio">Notas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Logros.jsp"><img src="img/icon.svg" class="ImagenesBarraInicio">Logros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Ayuda.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Ayuda</a>
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
                            <a class="dropdown-item" href="CerrarSesion.jsp"><img src="img/enter.svg" class="ImagenesBarraInicio" > Cerrar Sesion</a>
                            <a class="dropdown-item" href="Configuracion.jsp"><img src="img/settings-work-tool.svg" class="ImagenesBarraInicio" >
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
                    <div class="tab-content" id="ContenidoGrupos">
                        <%
                            //Traer el id del usaurio
                            String queryIDUsuario = "select IDUsuario from Usuario where NombreUsuario='"+nomUsuario+"';";
                            Statement stIDU = con.createStatement();
                            ResultSet rsIDU = stIDU.executeQuery(queryIDUsuario);
                            rsIDU.next();
                            int idUsuario = rsIDU.getInt("IDUsuario");
                            
                            //Para grupos
                            String query = "select * from Grupo inner join Miembros on Grupo.IDGrupo=Miembros.IDGrupo where Miembros.IDUsuario="+idUsuario+";";
                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery(query);
                            String nombreGrupo;
                            
                            //Para las tareas de los grupos
                            int idGrupo;
                            String queryTarea = "select * from Tarea where IDGrupo=?;";
                            PreparedStatement ps = con.prepareStatement(queryTarea);
                            ResultSet rsTarea;
                            int nTarea;
                            String nomTarea;
                            String fecha;
                            boolean estado;
                            String idConcatenada;
                            
                            //Para miembros asignados a una tarea
                            String queryMiembros = "select NombreUsuario from Usuario inner join Miembros on Usuario.IDUsuario=Miembros.IDUsuario inner join TareaMiembro on Miembros.IDMiembro=TareaMiembro.IDMiembro where TareaMiembro.IDTarea=?;";
                            PreparedStatement psMiembros = con.prepareStatement(queryMiembros);
                            ResultSet rsMiembros;
                            String nombreMiembro;
                            while (rs.next()) {
                                //Desplegando todos los grupos de usuario
                                nombreGrupo = (String) rs.getString("NombreGrupo");
                                idGrupo = rs.getInt("IDGrupo");
                        %>
                        <!--Inicio de un grupo-->
                        <div class="tab-pane fade grupo" <%out.println("id='panel-g" + nombreGrupo + "' role='tabpanel' aria-labelledby='lista-g" + nombreGrupo + "'");%>>

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
                                                    ps.setInt(1, idGrupo);
                                                    rsTarea = ps.executeQuery();
                                                    while(rsTarea.next()){
                                                        nTarea = rsTarea.getInt("IDTarea");
                                                        nomTarea = rsTarea.getString("Nombre");
                                                        fecha = rsTarea.getTimestamp("Fecha").toString();
                                                        estado = rsTarea.getBoolean("Estado");
                                                        idConcatenada = nTarea + nombreGrupo;
                                                        %>
                                                        <!--Inicio tarea-->
                                                        <div class="card mt-3">
                                                            <!--Inicio parte visible-->
                                                            <div class="card-header" <%out.print("id='headTarea-"+idConcatenada+"'");%>>
                                                                <div class="row">
                                                                    <!--Nombre tarea-->
                                                                    <div class="col-sm-3 d-flex align-items-center justify-content-center my-2">
                                                                        <h5 class="mb-0">
                                                                            <!--btn para hacer que se despliegue la parte invisible-->
                                                                            <button class="btn btn-link p-0 collapsed" data-toggle="collapse" 
                                                                            <%out.print("data-target='#tarea-"+idConcatenada+"'");%> aria-expanded="false" 
                                                                            <%out.print("aria-controls='tarea-"+idConcatenada+"'");%>>
                                                                                <span class="d-inline-block text-truncate" style="max-width: 150px;">
                                                                                <%out.print(nomTarea);%>
                                                                                </span>
                                                                            </button>
                                                                        </h5>
                                                                    </div>
                                                                    <!--Fecha limite-->
                                                                    <div class="col-sm-3 d-flex align-items-center justify-content-center my-2">
                                                                        <small class="text-muted" <%out.print("id='fechaTarea-"+idConcatenada+"'");%>>
                                                                            <%out.print(fecha);%>
                                                                        </small>
                                                                    </div>
                                                                    <!--Miembros-->
                                                                    <div class="col-sm-3 d-flex align-items-center justify-content-center">
                                                                        <div class="dropdown">
                                                                            <button class="btn btn-secondary dropdown-toggle" type="button" 
                                                                                    <%out.print("id='menuMiembros-"+idConcatenada+"'");%> data-toggle="dropdown" 
                                                                                    aria-haspopup="true" aria-expanded="false">
                                                                                    Miembros
                                                                            </button>
                                                                            <div class="dropdown-menu" aria-labelledby="menuMiembros my-2" 
                                                                                 id="dropdown-miembros-NombreGrupo">
                                                                                <%
                                                                                    psMiembros.setInt(1, nTarea);
                                                                                    rsMiembros = psMiembros.executeQuery();
                                                                                    if(rsMiembros.next()){
                                                                                        rsMiembros.beforeFirst();
                                                                                        while(rsMiembros.next()){
                                                                                            nombreMiembro = rsMiembros.getString("NombreUsuario");
                                                                                %>
                                                                                            <button class="dropdown-item disabled" type="button"><%out.print(nombreMiembro);%></button>
                                                                                <%
                                                                                        };
                                                                                        rsMiembros.close();//Fin del resulset que trae a los mimbros asignados a una tarea
                                                                                    } else {
                                                                                %>
                                                                                    <button class="dropdown-item disabled" type="button">No hay miembros asignados</button>
                                                                                <%  } %>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <!--Checkbox para marcar actividad-->
                                                                    <div class="col-sm-3 d-flex align-items-center justify-content-center my-2">
                                                                        <%if(estado) {%>
                                                                            <input class="acabarTarea float-right" type="checkbox" checked>
                                                                        <%} else {%>
                                                                            <input class="acabarTarea float-right checado" type="checkbox">
                                                                         <% } %>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--Fin parte visible-->
                                                            <!--Inicio parte colapsable-->
                                                            <div <%out.print("id='tarea-"+idConcatenada+"' class='collapse' aria-labelledby='headTarea-"+idConcatenada+"' data-parent='#listaTareas-"+nombreGrupo+"'");%>>
                                                                <div class="card-body">
                                                                    <form>
                                                                        <!--Inicio formulario para modificar actividad-->
                                                                        <div class="form-row">
                                                                            <div class="col-sm-3">
                                                                                <input type="text" class="form-control" id="tarea" name="tarea" placeholder="Cambiar nombre tarea">
                                                                            </div>
                                                                            <div class="col-sm-3">
                                                                                <input type="date" class="form-control" id="fecha" name="fecha">
                                                                            </div>
                                                                            <div class="col-sm-3">
                                                                                <input type="text" class="form-control" id="eliMiembro" name="eliMiembro" placeholder="Eliminar miembro">
                                                                            </div>

                                                                            <div class="col-sm-3">
                                                                                <input type="text" class="form-control" id="agrMiembro" name="agrMiembro" placeholder="Agregar miembro">
                                                                            </div>
                                                                        </div>
                                                                        <!--Fin formulario para modificar actividad-->
                                                                        <!--Inicio opciones-->
                                                                        <div class="form-row mt-2">
                                                                            <div class="col-sm-6">
                                                                                <button type="submit" class="btn btn-outline-warning mb-2" style="width: 100%;">Modificar</button>
                                                                            </div>
                                                                            <div class="col-sm-6">
                                                                                <button type="button" class="btn btn-outline-danger mb-2" style="width: 100%;">Eliminar</button>
                                                                            </div>
                                                                        </div>
                                                                        <!--Fin opciones-->
                                                                    </form>
                                                                </div>
                                                            </div>
                                                            <!--Fin parte colapsable-->
                                                        </div>
                                                        <!--Fin tarea-->
                                                        <%
                                                    };
                                                    rsTarea.close();//Fin del resulset que trae a todas las actividades de un grupo
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
                            rs.close();//Fin del resulset que trae a todos los grupos de un usuario
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
                                    <%
                                        //Agrego un listado de todos los grupos del usuario
                                        Statement st2 = con.createStatement();
                                        ResultSet rs2 = st2.executeQuery(query);
                                        query = "select * from Grupo inner join Usuario on Grupo.UsuarioLider=Usuario.IDUsuario inner join Miembros on Grupo.IDGrupo=Miembros.IDGrupo where Usuario.NombreUsuario='" + nomUsuario + "'";
                                        String grupo;
                                        while (rs2.next()) {
                                            //Desplegando el listado de grupos
                                            grupo = (String) rs2.getString("NombreGrupo");
                                    %>
                                    <a class="list-group-item list-group-item-action grupoLista" 
                                       <%out.println(" id='lista-g"+grupo+"' data-toggle='list' href='#panel-g"+grupo+"' role='tab' aria-controls='g"+grupo+"'");%>
                                    >
                                        <img src="img/group.svg" alt="ic_grupos">
                                        <%
                                            out.println(grupo);
                                        %>
                                    </a>
                                    <%
                                        }
                                        rs2.close();
                                    %>
                                    <!--
                                    <a class="list-group-item list-group-item-action grupoLista" id="lista-gNombreGrupo" data-toggle="list" href="#panel-gNombreGrupo" role="tab" aria-controls="gNombreGrupo">
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
                                                            <input type="text" class="form-control" placeholder="Nombre grupo" name="nuevoGrupo" id="nuevoGrupo">
                                                        </div>
                                                        <div class="col-9">
                                                            <input type="email" class="form-control" placeholder="Correo del integrante" name="miembro" id="miembro">
                                                        </div>
                                                        <div class="col-3">
                                                            <button class="form-control" type="reset" id="agregarMiembro">Agregar</button>
                                                        </div>
                                                        <div class="form-group col-12">
                                                            <ul class="form-control list-group agregarMiembro" id="ListaMiembros">
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
                                                        <button type="reset" class="btn btn-primary form-control" id="btnCrearGrupo" value='<%out.print(nomUsuario);%>'>Guardar</button>
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
            //Array de miembros de un nuevo grupo
            var miembrosNG = "";
            
            /*FUNCION PARA EL EVENTO DE AGREGAR UN MIEMBRO NUEVO A UN GRUPO NUEVO*/
            $("#agregarMiembro").click( //Agregar miembro al formulario para el nuevo grupo
                function() {
                    var nuevoMiembro = $("#miembro").val().toString();
                    
                    $.ajax( {
                        url: "AgregarMiembro",
                        data: {
                            correoMiembro: nuevoMiembro
                        },
                        type: 'post',
                        success: function (data) {//data trae el nombre del usuario
                            $("#ListaMiembros").prepend(//Para insertar html
                                $( //Todo el nuevo contenido
                                    "<li class='list-group-item d-flex justify-content-between align-items-center'>"+
                                        data.toString()+//El nombre del miembro alv
                                        "<span class='badge'>"+
                                            "<button type='button' class='close' aria-label='Close'>"+
                                                "<span aria-hidden='true'>&times;</span>"+
                                            "</button>"+
                                        "</span>"+
                                    "</li>"
                                )
                            );
                            miembrosNG=miembrosNG+","+data.toString();//Agregar el nombre del miembro al array
                        },
                        error: function () {
                            alert("Error buscando miembro");
                        },
                        complete: function () {
                        }
                    } );
                }
            );
    
            /*FUNCION PARA EL EVENTO DE CREAR UN GRUPO NUEVO*/
            $("#btnCrearGrupo").click(
                function() {
                    //Traigo el nombre del grupo
                    var nuevoGrupo = $("#nuevoGrupo").val().toString();
                    var usuario = $("#btnCrearGrupo").val().toString();
                    $.ajax( {
                        url: "CrearGrupo",
                        data: {//Envio el nombre del grupo y los miembros
                            nomNuevoGrupo: nuevoGrupo,
                            lider: usuario,
                            miembros: miembrosNG
                        },
                        type: 'post',
                        success: function () {
                            //Desaparecer elementos
                            $("div.grupo").removeClass("active");
                            $("a.grupoLista").removeClass("active");
                            //LADO DERECHO
                            $("#ContenidoGrupos").prepend( //Inserto a tarjeta que contiene al nuevo grupo
                                "<div class='tab-pane fade show grupo active' id='panel-g"+nuevoGrupo+"' role='tabpanel' aria-labelledby='lista-g"+nuevoGrupo+"'>"+
                                        //Inicio titulo contenedor
                                        "<div class='card-deck'>"+
                                            "<div class='card Contenedor'>"+
                                                "<div class='card-body'>"+
                                                    "<div class='row'>"+
                                                        "<div class='col d-flex align-items-center justify-content-center'>"+
                                                            nuevoGrupo+
                                                        "</div>"+
                                                    "</div>"+
                                                "</div>"+
                                            "</div>"+
                                        "</div>"+
                                        //Fin titulo contenedor
                                        //Inicio cuerpo contenedor
                                        "<div class='card-deck'>"+
                                            "<div class='card'>"+
                                                "<div class='card-body'>"+
                                                    //Inicio agregar tarea
                                                    "<form>"+
                                                        "<div class='form-row align-items-center'>"+
                                                            "<div class='col-sm-5 mt-2'>"+
                                                                "<input type='text' class='form-control' placeholder='Ingresa una tarea'>"+
                                                            "</div>"+
                                                            "<div class='col-sm-5 mt-2'>"+
                                                                "<div class='input-group'>"+
                                                                    "<div class='input-group-prepend'>"+
                                                                        "<div class='input-group-text'>@</div>"+
                                                                    "</div>"+
                                                                    "<input type='text' class='form-control' placeholder='Correo'>"+
                                                                "</div>"+
                                                            "</div>"+
                                                            "<div class='col-sm-2 d-flex justify-content-center mt-2'>"+
                                                                "<button type='submit' class='btn btn-primary'>Agregar</button>"+
                                                            "</div>"+
                                                        "</div>"+
                                                    "</form>"+
                                                    //Fin agregar tarea
                                                    //Inicio contenedor de lista de tareas
                                                    "<div class='row rowListaTareas'>"+
                                                        //Inicio lista de tareas
                                                        "<div class='col-12' id='listaTareas-"+nuevoGrupo+"'"+
                                                        "</div>"+
                                                        //Fin lista de tareas
                                                    "</div>"+
                                                    //Fin contenedor de lista de tareas
                                                "</div>"+
                                            "</div>"+
                                        "</div>"+
                                        //Fin cuerpo contenedor
                                "</div>"
                            );
                            //LADO IZQUIERDO
                            $("#list-tab").prepend(//Inserto el grupo a la lista de grupos
                                "<a class='list-group-item list-group-item-action active grupoLista' id='lista-g"+nuevoGrupo+"' data-toggle='list' href='#panel-g"+nuevoGrupo+"' role='tab' aria-controls='g"+nuevoGrupo+"'>"+
                                        "<img src='img/group.svg' alt='ic_grupos'>"+
                                        nuevoGrupo+
                                "</a>"
                            );
                        },
                        error: function () {
                            alert("Hubo un error :'v ");
                        },
                        complete: function () {
                        }
                    } );
                }
            );
    
           
        </script>
    </body>
</html>
