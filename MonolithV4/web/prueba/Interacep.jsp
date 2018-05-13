<%-- 
    Document   : Interacep
    Created on : 12/05/2018, 07:10:34 PM
    Author     : INSPIRON
--%>
<%@page import="java.sql.*, java.io.*,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../Css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="../Css/BarraDeInicioSesion.css" rel="stylesheet" type="text/css">
        <link href="../Css/GruposCSS.css" rel="stylesheet" type="text/css">
        <link href="../Css/estilosperros.css" rel="stylesheet" type="text/css">
        <script src="../js/jquery-3.2.1.min.js"></script>
        <script src="../js/popper.min.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/jquery.validate.js"></script>
        <%
            String usuario = request.getParameter("usuario");
        %>
        <script>

            function changecontent() {
                var texto = document.getElementById("memo").value;
                var imagen = texto.substring(4, 6);
                var filtro = texto.substring(14, 16);
                var id = subStrAfterChars(texto, 'M', 'b');
                var imagenchida = "../img/dogo" + imagen + ".jpg";
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
            function mandardatosuwu() {
                var texto = document.getElementById("memo").value;
                var xd = "nel";
                if (texto == xd) {
                    alert('No seleccionaste un doggo >:c');
                } else {
                    document.forms['respeta'].submit();
                }
            }

        </script>
    </head>
    <body style="background-color: #fff">

        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form class="col-6" method="post" name="Aceptar"  action="Ofertas.jsp?usuario=<%=usuario%>">
                        <button type="submit" class="btn btn-primary"  >Regresar</button>
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <%
                        int IDusuario = 0;
                        int IDInter = Integer.parseInt(request.getParameter("id"));%>
                    <div class="card-group mt-3">
                        <div class="card">
                            <form action="Solireal.jsp?id=<%=IDInter%>" name="respeta" method="post">

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
                                            String filtro = "";
                                            String img = "";
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
                                                int filaux;
                                                int imgaux;
                                                while (r.next()) {
                                                    estado = "";
                                                    coun++;
                                                    nombre = r.getString("Nombre");
                                                    id = Integer.parseInt(r.getString("IDLogro"));
                                                    filaux = Integer.parseInt(r.getString("Filtro"));
                                                    if (filaux < 10) {
                                                        filtro = "0" + filaux;
                                                    } else {
                                                        filtro = "" + filaux;
                                                    }
                                                    imgaux = Integer.parseInt(r.getString("Img"));
                                                    if (imgaux < 10) {
                                                        img = "0" + imgaux;
                                                    } else {
                                                        img = "" + imgaux;
                                                    }
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
                                    <img name="marco" id="marco" src="../img/Doggo.jpg" class="" alt="a" width=250px" height="250px">
                                </div>

                                <input class="btn btn-primary" type="button" onclick="mandardatosuwu();" value="Intercambiar" <%=estado%>>
                            </form>
                        </div>

                        <div class="card">
                            <br>
                            <br><br><br>
                            <img src="../img/inter.png" class="card-image-center img-fluid" alt="">

                        </div>

                        <div class="card">
                            <%
                                String dogoda = "";
                                int IDdogoda = 0;
                                String dogofiltro = "";
                                String imgDa = "";
                                String UsuDa1 = "";
                                int memoaux = 0;
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
                                        memoaux = Integer.parseInt(r.getString("Img"));
                                        if (memoaux < 10) {
                                            imgDa = "0" + memoaux;
                                        } else {
                                            imgDa = "" + memoaux;
                                        }
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
                                <img name="marco1" id="marco1" src="../img/dogo<%=imgDa%>.jpg" class="<%=dogofiltro%>" alt="" width=250px" height="250px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
