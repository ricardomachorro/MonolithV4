<%-- 
    Document   : SoliAcep
    Created on : 28/03/2018, 12:58:26 PM
    Author     : memo0
--%>
<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Intercambio</title>
        <link href="Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="Css/logros.css" rel="stylesheet" type="text/css">
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
           function mandardatosuwu(){
               var texto = document.getElementById("memo").value;
               var xd = "nel";
               if(texto==xd){
                   alert('No seleccionaste un doggo >:c');
               }else{
                   document.forms['respeta'].submit();
               }
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
                        <a class="nav-link"  href=""><img src="img/group.svg" class="ImagenesBarraInicio" >Grupo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href=""><img src="img/post-it.svg" class="ImagenesBarraInicio" >Notas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="LogrosPrue.jsp"><img src="img/post-it.svg" class="ImagenesBarraInicio" >Logros</a>
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

            <div class="row">
                <div class="col">
                    <%
                        int IDusuario = 0;
                        int IDInter = Integer.parseInt(request.getParameter("id"));%>
                    <div class="card-group mt-3">
                        <div class="card">
                            <form action="AcepLogroa?id=<%=IDInter%>" name="respeta" method="post">

                                <div class="form-group">
                                    <h2><label for="nombre">Selecciona el dogo que deseas intercambiar</label></h2>
                                    <select name="memo" id="memo" class="custom-select" onChange="changecontent(this)" required>
                                        <option value="nel" selected>Seleciona al dogo</option>
                                        <%

                                            Connection con = null;
                                            Statement sta = null;
                                            ResultSet r = null;
                                            String nombre = "";
                                            int id = 0;
                                            int coun = 1;
                                            int filtro = 0;
                                            int img = 0;
                                            //esta mamada >:V
                                            Calendar fechita = new GregorianCalendar();
                                            int año = fechita.get(Calendar.YEAR);
                                            int mes = fechita.get(Calendar.MONTH);
                                            int dia = fechita.get(Calendar.DAY_OF_MONTH);
                                            int meschido = mes + 1;
                                            String fecha1 = "" + año + "-" + meschido + "-" + dia;
                                            String estado = "";
                                            String perroDa = "";

                                            try {
                                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                                                sta = con.createStatement();
                                                r = sta.executeQuery("select * from Usuario where NombreUsuario='" + usuario + "'");

                                                if (r.next()) {
                                                    IDusuario = Integer.parseInt(r.getString("IDUsuario"));
                                                }
                                                r = sta.executeQuery("select * from Intercambio where IDInter=" + IDInter + "");
                                                while (r.next()) {
                                                    perroDa = "dogo #" + r.getString("dogoRe");
                                                }
                                                r = sta.executeQuery("select * from Logro where IDusuario=" + IDusuario + " and Nombre='" + perroDa + "'");

                                                while (r.next()) {
                                                    estado = "";
                                                    coun++;
                                                    nombre = r.getString("Nombre");
                                                    id = Integer.parseInt(r.getString("IDLogro"));
                                                    filtro = Integer.parseInt(r.getString("Filtro"));
                                                    img = Integer.parseInt(r.getString("Img"));
                                        %>
                                        <option value="img:<%=img%> filtro:<%=filtro%> id:M<%=id%>" ><%=nombre%> filtro<%=filtro%></option>

                                        <%
                                            }
                                            if (coun == 1) {
                                                estado = "disabled";
                                        %>
                                        <option value="sorry">No tiene al dogo requerido</option>
                                        <%}
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

                                        <input class="btn btn-primary" type="button" onclick="mandardatosuwu();" value="Intercambiar" <%=estado%>>
                            </form>
                        </div>

                        <div class="card">
                            <br>
                            <br><br><br>
                            <img src="img/inter.png" class="card-image-center img-fluid" alt="">

                        </div>

                        <div class="card">
                            <%
                                String dogoda = "";
                                int IDdogoda = 0;
                                String dogofiltro = "";
                                int imgDa = 0;
                                String UsuDa1 = "";
                                try {
                                    r = sta.executeQuery("select * from Intercambio where IDInter=" + IDInter + "");
                                    while (r.next()) {
                                        UsuDa1 = r.getString("UsuarioDa");
                                        dogoda = r.getString("dogoDa");
                                        IDdogoda = Integer.parseInt(r.getString("IDdogoDa"));
                                        dogofiltro = r.getString("FiltroDa");
                                    }
                                    r = sta.executeQuery("select * from Logro where IDLogro=" + IDdogoda + "");
                                    while (r.next()) {
                                        imgDa = Integer.parseInt(r.getString("Img"));
                                    }

                                } catch (SQLException error) {
                                    out.print(error.toString());
                                }
                            %>
                            <h3><label for="nombre">Dogo ofrecido por <%=UsuDa1%></label></h3>
                            <div class="form-group">
                                <label for="mensaje">Nombre</label>
                                <input type="text" name="nom" id="nom" placeholder="nombre" class="form-control" value="<%=dogoda%>"readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="mensaje">ID</label>
                                <input type="text" name="IDimg1" id="IDimg1" placeholder="IDimg1" class="form-control"value="<%=IDdogoda%>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="mensaje">Filtro</label>
                                <input type="text" name="filtr1o" id="filtro1" placeholder="filtro1" class="form-control"value="<%=dogofiltro%>"readonly="readonly">
                            </div>
                            <div class="form-group">
                                <img name="marco1" id="marco1" src="img/dogo<%=imgDa%>.jpg" class="<%=dogofiltro%>" alt="" width=250px" height="250px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
    </body>
</html>

