<%-- 
    Document   : Inter
    Created on : 11/05/2018, 06:19:31 PM
    Author     : INSPIRON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.io.*,java.util.*"%>
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

                var imagen = texto.substring(4, 6);
                var filtro = texto.substring(14, 16);
                var id = subStrAfterChars(texto, 'M', 'b');
                var imagenchida = "../img/dogo" + imagen.trim() + ".jpg";
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
    <body style="background-color: #fff">
        <%
            String usuario = request.getParameter("usuario");
        %>
        <div class="container">
            <div class="row">
                <div class="col">
                    <form action="Inter1.jsp?usuario=<%=usuario%>" method="Post">

                        <div class="form-group">
                            <label for="nombre">Selecciona el dogo que deseas intercambiar</label>
                            <select name="memop2" id="memop2" class="custom-select" onChange="changecontent(this)" required="">
                                <option value="hola" selected>Seleciona al dogo</option>
                                <%
                                    Connection con = null;
                                    Statement sta = null;
                                    ResultSet r = null;
                                    int IDusuario3 = 0;
                                    String nombre3 = "";
                                    int id3 = 0;
                                    int coun3 = 1;
                                    String filtro3 = "";
                                    String img3 = "";
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                                        con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                                        sta = con.createStatement();
                                        r = sta.executeQuery("select * from Usuario where NombreUsuario='" + usuario + "'");
                                        if (r.next()) {
                                            IDusuario3 = Integer.parseInt(r.getString("IDUsuario"));
                                        }
                                        r = sta.executeQuery("select * from Logro where IDusuario='" + IDusuario3 + "'");
                                        int filaux;
                                        int imgaux;
                                        while (r.next()) {
                                            coun3++;
                                            nombre3 = r.getString("Nombre");
                                            id3 = Integer.parseInt(r.getString("IDLogro"));
                                            filaux=Integer.parseInt(r.getString("Filtro"));
                                            if(filaux<10){
                                                filtro3="0"+filaux;
                                            }else{
                                                filtro3=""+filaux;
                                            }
                                            imgaux= Integer.parseInt(r.getString("Img"));
                                            if(imgaux<10){
                                                img3="0"+imgaux;
                                            }else{
                                               img3 = ""+imgaux; 
                                            }
                                                
                                            
                                %>
                                <option value="img:<%=img3%> filtro:<%=filtro3%> id:M<%=id3%>" ><%=nombre3%> filtro<%=filtro3%></option>

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
                            <img name="marco" id="marco" src="../img/Doggo.jpg" class="" alt="" width=250px" height="250px">
                        </div>
                        <div class="form-group">
                            <label for="mensaje">Usuario a quien se lo propones</label>
                            <input type="text" name="UsuarioRe" id="UsuarioRe" placeholder="UsuarioRe" class="form-control" onkeypress="return soloenteros(event);"onpaste="return false"required="">
                        </div>

                        <div class="form-group">
                            <label for="mensaje">Numero que deseas</label>
                            <input type="number" name="numRe" id="numRe" placeholder="numRe" class="form-control" onpaste="return false" onkeypress="return solonumeros(event);" required="">
                        </div>
                        <input class="btn btn-primary" type="submit">
                    </form>
                </div>
            </div>

        </div>
    </body>
</html>
