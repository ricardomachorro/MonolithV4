<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logros</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/logros.css" rel="stylesheet" type="text/css">
        <link href="Css/estilosperros.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script>
            function solonumeros(e) {
                var keyword;
                if (window.event) {
                    keyword = e.keyCode;//explorer
                } else
                if (e.which) {
                    keyword = e.which;//fire fox / opera
                }
                if ((keyword === 8) || (keyword === 32) || (keyword === 95) || (keyword >= 48 && keyword <= 57)) {
                    return true;
                } else
                    return false;
            }
            function soloenteros(e) {
                var keyword;
                if (window.event) {
                    keyword = e.keyCode;//explorer
                } else
                if (e.which) {
                    keyword = e.which;//fire fox / opera
                }
                if ((keyword >= 65 && keyword <= 90) || (keyword >= 97 && keyword <= 122) || (keyword === 8) || (keyword === 32) || (keyword === 95) || (keyword >= 48 && keyword <= 57)) {
                    return true;
                } else
                    return false;
            }
            function changecontent() {
                var texto = document.getElementById("memop2").value;

                var imagen = texto.substring(4, 5);
                var filtro = texto.substring(13, 15);
                var id = subStrAfterChars(texto, 'M', 'b');
                var imagenchida = "img/dogo" + imagen + ".jpg";
                objFondo = document.getElementById("marco");
                marco.src = imagenchida;
                document.getElementById("marco").className = "filtro" + filtro;
                document.getElementById("IDimg").value = id;
                document.getElementById("filtro").value = "filtro" + filtro;
            }
            function subStrAfterChars(str, char, pos)
            {
                if (pos == 'b')
                    return str.substring(str.indexOf(char) + 1);
                else if (pos == 'a')
                    return str.substring(0, str.indexOf(char));
                else
                    return str;
            }

        </script>
        <%
            HttpSession sesion = request.getSession();

            if (sesion.getAttribute("usuario") == null) {
                out.println("<script>location.replace('index.html');</script>");
            } else {
                String usuario = sesion.getAttribute("usuario").toString();
        %>
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
                        <a class="nav-link"  href="LogrosPrue.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Logros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Ayuda.jsp"><img src="img/support.svg" class="ImagenesBarraInicio">Ayuda</a>
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
        <%
            int IDusuario = 0;
            Connection con = null;
            Statement sta = null;
            ResultSet r = null;
            String nombre = "";
            String fecha = "";
            String perrito = "";
            int numpe = 0;
            String fil = "";
            int puntos = 0;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                sta = con.createStatement();
                r = sta.executeQuery("select * from Usuario where NombreUsuario='" + usuario + "'");
                if (r.next()) {
                    IDusuario = Integer.parseInt(r.getString("IDUsuario"));
                    puntos = Integer.parseInt(r.getString("Puntos"));
                }

        %>
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
                                        <img src="img/footprint.svg"  class="float-left ImagenesResumenActividades" >
                                    </div>
                                    <div class="col-8 TextoCartasResumenActividades" >
                                        <a >Agregar dogo</a>
                                        <%                                            if (puntos >= 5) {
                                        %>
                                        <form action="Agregar" method="Post">
                                            <input type="submit" class="btn btn-primary" value="Agregar">
                                        </form> 
                                        <%
                                        } else {%>
                                        <input type="submit" class="btn btn-danger" value="sin puntos">
                                        <%
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
                                        Cada dogo cuestan 5 puntos
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card CartasResumenActividades" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-4">
                                        <img src="img/coins.svg"  class="float-left ImagenesResumenActividades" >
                                    </div>
                                    <div class="col-8 TextoCartasResumenActividades" >
                                        <a >Tienes <%=puntos%> puntos</a>
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
                                        <h1>Logros de <%=usuario%></h1>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4">
                                        <img id="ImagenBandejaPrincipal" class="float-right" src="img/dog.svg">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Fin Titulo Bandeja-->

                    <div class="card-deck">
                        <div class="card" >
                            <div class="card-body">
                                <!--Bandeja Actividades-->
                                <div class="card-columns">

                                    <%
                                        r = sta.executeQuery("select * from Logro where IDUsuario='" + IDusuario + "'");
                                        while (r.next()) {
                                            numpe = Integer.parseInt(r.getString("Img"));
                                            fil = "filtro" + r.getString("Filtro");
                                            perrito = "dogo" + numpe + ".jpg";
                                            nombre = r.getString("Nombre");
                                            fecha = r.getString("fecha");

                                    %>
                                    <div class="card">
                                        <img src="img/<%=perrito%>" class="card-img-top <%=fil%>" alt="" width=250px" height="250px">
                                        <div class="card-body">
                                            <h5 class="card-title"><%=nombre%></h5>
                                            <p>El dogo se consiguio el <%=fecha%></p>

                                        </div>
                                    </div>

                                    <%   }

                                    %>


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
                                        <h1>Ofertas</h1>
                                        <button class="btn btn-primary" data-toggle="modal" data-target="#fm-modal">Hacer oferta</button>

                                        <div class="modal fade" id="fm-modal" tabindex="-1" role="dialog" aria-labelledby="fm-modal" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="">Intercambio</h5>
                                                        <button class="close" data-dismiss="modal" aria-label="Cerrar">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>

                                                    <div class="modal-body">
                                                        <form action="Inter" method="Post">

                                                            <div class="form-group">
                                                                <label for="nombre">Selecciona el dogo que deseas intercambiar</label>
                                                                <select name="memop2" id="memop2" class="custom-select" onChange="changecontent(this)" required="">
                                                                    <option value="hola" selected>Seleciona al dogo</option>
                                                                    <%                                                                        int IDusuario3 = 0;

                                                                        String nombre3 = "";
                                                                        int id3 = 0;
                                                                        int coun3 = 1;
                                                                        int filtro3 = 0;
                                                                        int img3 = 0;
                                                                        r = sta.executeQuery("select * from Usuario where NombreUsuario='" + usuario + "'");
                                                                        if (r.next()) {
                                                                            IDusuario3 = Integer.parseInt(r.getString("IDUsuario"));
                                                                        }
                                                                        r = sta.executeQuery("select * from Logro where IDusuario='" + IDusuario3 + "'");
                                                                        while (r.next()) {
                                                                            coun3++;
                                                                            nombre3 = r.getString("Nombre");
                                                                            id3 = Integer.parseInt(r.getString("IDLogro"));
                                                                            filtro3 = Integer.parseInt(r.getString("Filtro"));
                                                                            img3 = Integer.parseInt(r.getString("Img"));
                                                                    %>
                                                                    <option value="img:<%=img3%> filtro:<%=filtro3%> id:M<%=id3%>" ><%=nombre3%> filtro<%=filtro3%></option>

                                                                    <%
                                                                        }

                                                                    %>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="mensaje">ID</label>
                                                                <input type="text" name="IDimg" id="IDimg" placeholder="IDimg" class="form-control"readonly="readonly">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="mensaje">Filtro</label>
                                                                <input type="text" name="filtro" id="filtro" placeholder="filtro" class="form-control"readonly="readonly">
                                                            </div>
                                                            <div class="form-group">
                                                                <img name="marco" id="marco" src="img/Doggo.jpg" class="" alt="" width=250px" height="250px">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="mensaje">Usuario a quien se lo propones</label>
                                                                <input type="text" name="UsuarioRe" id="UsuarioRe" placeholder="UsuarioRe" class="form-control" onkeypress="return soloenteros(event);"onpaste="return false"required="">
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="mensaje">Numero que deseas</label>
                                                                <input type="text" name="numRe" id="numRe" placeholder="numRe" class="form-control" onpaste="return false" onkeypress="return solonumeros(event);" required="">
                                                            </div>
                                                            <input class="btn btn-primary" type="submit">
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4">
                                        <img class="float-right" src="img/shuffle.svg" height="50px" width="50px">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-deck">
                        <div class="card ListasLaterales"  >
                            <div class="card-body" >
                                <div id="acordion" role="tablist" aria-multiselectable="true">
                                    <%                                        int idusuda = 0;
                                        int idusure = 0;
                                        String usuda = "";
                                        String usure = "";
                                        int iddogoda = 0;
                                        int iddogore = 0;
                                        String dogoda = "";
                                        int dogore = 0;
                                        int IDinter = 0;
                                        String filtroDa = "";
                                        String estado = "";
                                        String fecha1 = "";
                                        int conta = 0;
                                        int imgchida = 0;
                                        r = sta.executeQuery("select * from Intercambio where UsuarioRe='" + usuario + "' and Estado='proceso'  order by IDInter DESC");
                                        while (r.next()) {

                                            conta++;
                                            IDinter = Integer.parseInt(r.getString("IDInter"));
                                            idusuda = Integer.parseInt(r.getString("IDusuarioDa"));
                                            idusure = Integer.parseInt(r.getString("IDusuarioRe"));
                                            usuda = r.getString("UsuarioDa");
                                            usure = r.getString("UsuarioRe");
                                            iddogoda = Integer.parseInt(r.getString("IDdogoDa"));
                                            iddogore = Integer.parseInt(r.getString("IDdogoRe"));
                                            filtroDa = r.getString("FiltroDa");
                                            imgchida = Integer.parseInt(r.getString("ImgdogoDa"));
                                            dogoda = r.getString("dogoDa");
                                            dogore = Integer.parseInt(r.getString("dogoRe"));
                                            estado = r.getString("Estado");
                                            fecha1 = r.getString("fecha");
                                    %>
                                    <div class="card">
                                        <div class="card-header" role="tab" id="heading<%=conta%>">
                                            <h5 class="mb-0">
                                                <a href="#collapse<%=conta%>" data-toggle="collapse" data-parent="#acordion" aria-expanded="true" aria-controls="collapse<%=conta%>">
                                                    <p id="usuda" name="usuda">Oferta de: <%=usuda%></p>
                                                </a>
                                            </h5>


                                            <div class="row">
                                                <form class="col-6" method="post" name="Aceptar"  action="SoliAcep.jsp?id=<%=IDinter%>">
                                                    <button type="submit" class="btn btn-primary"  >Aceptar</button>
                                                </form>
                                                <form class="col-6" method="post" name="Negar"  action="NegarInter?id=<%=IDinter%>">
                                                    <button type="submit" class="btn btn-danger">Negar</button>
                                                </form>
                                            </div>

                                        </div>

                                        <div id="collapse<%=conta%>" class="collapse" role="tabpanel" aria-labelledby="heading<%=conta%>">
                                            <div class="card-body">
                                                <p id="fecha" name="fecha"><%=fecha%></p>
                                                <p id="dogoda" name="dogoda">Te ofrece el <%=dogoda%></p>
                                                <p id="dogore" name="dogoda"> por el dogo# <%=dogore%></p>
                                                <p id="estado" name="estado">Estado: <%=estado%>

                                            </div>
                                            <div class="col-4">
                                                <img src="img/dogo<%=imgchida%>.jpg" class="card-img-top img-fluid <%=filtroDa%>" alt="" >



                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-deck">
                        <div class="card ContenedoresLaterales"  >
                            <div class="card-body" >
                                <div class="row">
                                    <div class="col-sm-6 col-md-8 col-lg-8">
                                        <h1>Solicitudes</h1>
                                    </div>
                                    <div class="col-sm-6 col-md-4 col-lg-4">
                                        <img class="float-right" src="img/reply.svg"height="50px" width="50px">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-deck">
                        <div class="card ListasLaterales"  >
                            <div class="card-body" >
                                <div id="memo" role="tablist" aria-multiselectable="true">
                                    <%
                                        int IDusuario1 = 0;
                                        int idusuda1 = 0;
                                        int idusure1 = 0;
                                        String usuda1 = "";
                                        String usure1 = "";
                                        int iddogoda1 = 0;
                                        int iddogore1 = 0;
                                        String dogoda1 = "";
                                        int dogore1 = 0;
                                        String filtroDa1 = "";
                                        String estado1 = "";
                                        String fecha2 = "";
                                        int conta1 = 0;

                                        int imgchida1 = 0;
                                        r = sta.executeQuery("select * from Intercambio where UsuarioDa='" + usuario + "' or UsuarioRe='" + usuario + "'  order by IDInter DESC  ");
                                        while (r.next()) {
                                            String tipo = "";
                                            String memo = "";
                                            conta1++;
                                            idusuda1 = Integer.parseInt(r.getString("IDusuarioDa"));
                                            idusure1 = Integer.parseInt(r.getString("IDusuarioRe"));
                                            usuda1 = r.getString("UsuarioDa");
                                            usure1 = r.getString("UsuarioRe");
                                            iddogoda1 = Integer.parseInt(r.getString("IDdogoDa"));
                                            iddogore1 = Integer.parseInt(r.getString("IDdogoRe"));
                                            filtroDa1 = r.getString("FiltroDa");
                                            imgchida1 = Integer.parseInt(r.getString("ImgdogoDa"));
                                            dogoda1 = r.getString("dogoDa");
                                            dogore1 = Integer.parseInt(r.getString("dogoRe"));
                                            estado1 = r.getString("Estado");
                                            fecha2 = r.getString("fecha");
                                            if (estado1.equals("proceso")) {
                                                tipo = "primary";
                                            } else {
                                                if (estado1.equals("aceptada")) {
                                                    tipo = "success";
                                                } else {
                                                    if (estado1.equals("negado")) {
                                                        tipo = "danger";
                                                    }
                                                }
                                            }
                                            if (usuda1.equals(usuario)) {
                                                memo = "Intercambio";
                                            } else {
                                                if (usure1.equals(usuario)) {
                                                    memo = "Solicitud";
                                                }
                                            }
                                    %>
                                    <div class="card">
                                        <div class="card-header" role="tab" id="heading1<%=conta1%>">
                                            <h5 class="mb-0">
                                                <a href="#collapse1<%=conta1%>" data-toggle="collapse" data-parent="#memo" aria-expanded="true" aria-controls="collapse<%=conta1%>">
                                                    <p id="usuda" name="usuda"><%=memo%> hacia <%=usure1%></p>
                                                </a>
                                                <a type="button" class="btn btn-<%=tipo%>">Estado: <%=estado1%></a>
                                            </h5>

                                        </div>

                                        <div id="collapse1<%=conta1%>" class="collapse" role="tabpanel" aria-labelledby="heading1<%=conta1%>">
                                            <div class="card-body">
                                                <p id="fecha" name="fecha"><%=fecha2%></p>
                                                <p id="dogoda" name="dogoda"><%=usuda1%> ofrece el <%=dogoda1%></p>
                                                <p id="dogore" name="dogoda">a <%=usure1%> por el dogo# <%=dogore1%></p>


                                            </div>
                                            <div class="col-4">
                                                <img src="img/dogo<%=imgchida1%>.jpg" class="card-img-top img-fluid <%=filtroDa1%>" alt="" width=250px" height="250px">

                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Fin Contenedor Lateral-->
                <%                    } catch (SQLException error) {
                            out.print(error.toString());
                        }
                    }

                %>

            </div>
        </div>
    </body>
</html>
