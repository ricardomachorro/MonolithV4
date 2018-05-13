<%-- 
    Document   : Solicitudes
    Created on : 12/05/2018, 06:14:00 PM
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
    </head>
    <body style="background-color: #fff">
        <div class="card-deck">
            <div class="card ListasLaterales"  >
                <div class="card-body" >
                    <div id="memo" role="tablist" aria-multiselectable="true">
                        <%
                            Connection con = null;
                            Statement sta = null;
                            ResultSet r = null;
                            String usuario = request.getParameter("usuario");
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
                            int filtrodamemo = 0;
                            String imgchida1 = "";
                            int imgmemo = 0;
                            try {
                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                                sta = con.createStatement();
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
                                    imgmemo = Integer.parseInt(r.getString("ImgdogoDa"));
                                    if (imgmemo < 10) {
                                        imgchida1 = "0" + imgmemo;
                                    } else {
                                        imgchida1 = "" + imgmemo;
                                    }
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
                                    <img src="../img/dogo<%=imgchida1%>.jpg" class="card-img-top img-fluid <%=filtroDa1%>" alt="" width=250px" height="250px">

                                </div>
                            </div>
                        </div>
                        <%}
                            } catch (SQLException error) {
                                out.print(error.toString());
                            }%>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
