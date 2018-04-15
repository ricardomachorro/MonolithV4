<%-- 
    Document   : Inter
    Created on : 27/03/2018, 07:33:51 PM
    Author     : memo0
--%>

<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Intercambios</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/GruposCSS.css" rel="stylesheet" type="text/css">
        <link href="Css/estilosperros.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("usuario") == null) {
                out.println("<script>location.replace('index.html');</script>");
            } else {
                String usuario = sesion.getAttribute("usuario").toString();
        %>
        <script>
            function soloenteros(e) {
                var keyword;
                if (window.event) {
                    keyword = e.keyCode;//explorer
                } else
                if (e.which) {
                    keyword = e.which;//fire fox / opera
                }
                if ((keyword >= 65 && keyword <= 90) || (keyword >= 97 && keyword <= 122) || (keyword === 8) || (keyword === 32) || (keyword >= 48 && keyword <= 57)) {
                    return true;
                } else
                    return false;
            }
            function changecontent() {
                var texto = document.getElementById("memo").value;
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
                        <a class="nav-link"  href="grupos.jsp"><img src="img/group.svg" class="ImagenesBarraInicio" >Grupo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Notas.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Notas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="Logros.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Logros</a>
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
                            <a class="dropdown-item" href="CerrarSesion.jsp"><img src="img/enter.svg" class="ImagenesBarraInicio" > Cerrar Sesion</a>
                            <a class="dropdown-item" href="Configuracion.jsp"><img src="img/settings-work-tool.svg" class="ImagenesBarraInicio" >
                                Configuracion</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="row ">
                <div class="col-12">
                    <center><h1>Ofertas de <%=usuario%></h1></center>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#fm-modal">Hacer oferta</button>
                    <a type="button" class="btn btn-dark" href="Intersoli.jsp">Solicitudes</a>
                </div>
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
                                <form action="Inter1.jsp" method="post">

                                    <div class="form-group">
                                        <label for="nombre">Selecciona el dogo que deseas intercambiar</label>
                                        <select name="memo" id="memo" class="custom-select" onChange="changecontent(this)" required="">
                                            <option value="hola" selected>Seleciona al dogo</option>
                                            <%
                                                int IDusuario = 0;
                                                Connection con = null;
                                                Statement sta = null;
                                                ResultSet r = null;
                                                String nombre = "";
                                                int id = 0;
                                                int coun = 1;
                                                int filtro = 0;
                                                int img = 0;
                                                try {
                                                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                                                    con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                                                    sta = con.createStatement();
                                                    r = sta.executeQuery("select * from Usuario where NombreUsuario='" + usuario + "'");
                                                    if (r.next()) {
                                                        IDusuario = Integer.parseInt(r.getString("IDUsuario"));
                                                    }
                                                    r = sta.executeQuery("select * from Logro where IDusuario='" + IDusuario + "'");
                                                    while (r.next()) {
                                                        coun++;
                                                        nombre = r.getString("Nombre");
                                                        id = Integer.parseInt(r.getString("IDLogro"));
                                                        filtro = Integer.parseInt(r.getString("Filtro"));
                                                        img = Integer.parseInt(r.getString("Img"));
                                            %>
                                            <option value="img:<%=img%> filtro:<%=filtro%> id:M<%=id%>" ><%=nombre%> filtro<%=filtro%></option>

                                            <%
                                                    }
                                                } catch (SQLException error) {
                                                    out.print(error.toString());
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
                                        <input type="number" name="numRe" id="numRe" placeholder="numRe" class="form-control" onpaste="return false" required="">
                                    </div>
                                    <input class="btn btn-primary" type="submit">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h3>Ofertas:</h3>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div id="acordion" role="tablist" aria-multiselectable="true">
                        <%
                            try {
                                int idusuda = 0;
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
                                String fecha = "";
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
                                    fecha = r.getString("fecha");
                        %>
                        <div class="card">
                            <div class="card-header" role="tab" id="heading<%=conta%>">
                                <h5 class="mb-0">
                                    <a href="#collapse<%=conta%>" data-toggle="collapse" data-parent="#acordion" aria-expanded="true" aria-controls="collapse<%=conta%>">
                                        Oferta de <p id="usuda" name="usuda"><%=usuda%></p>
                                    </a>
                                </h5>

                            </div>

                            <div id="collapse<%=conta%>" class="collapse" role="tabpanel" aria-labelledby="heading<%=conta%>">
                                <div class="card-body">
                                    <p id="fecha" name="fecha"><%=fecha%></p>
                                    Te ofrece el <p id="dogoda" name="dogoda"><%=dogoda%></p>
                                    por el dogo#<p id="dogore" name="dogoda"><%=dogore%></p>
                                    Estado:<p id="estado" name="estado"><%=estado%>

                                </div>
                                <div class="col-4">
                                    <img src="img/dogo<%=imgchida%>.jpg" class="card-img-top img-fluid <%=filtroDa%>" alt="" width=250px" height="250px">
                                    <form method="post" action="SoliAcep.jsp?id=<%=IDinter%>">
                                        <button type="submit" class="btn btn-primary" >Aceptar</button>
                                    </form>
                                    <form method="post" action="SoliNega.jsp?id=<%=IDinter%>">
                                        <button type="submit" class="btn btn-danger" >Negar</button>
                                    </form>

                                </div>
                            </div>
                        </div>

                        <%}
                                } catch (SQLException error) {
                                    out.print(error.toString());
                                }
                            }
                        %>


                    </div>
                </div>
            </div>
        </div>

    </body>
</html>