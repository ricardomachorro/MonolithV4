<%-- 
    Document   : Ofertas
    Created on : 12/05/2018, 05:49:14 PM
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
                    <div id="acordion" role="tablist" aria-multiselectable="true">
                        <%
                            Connection con = null;
                            Statement sta = null;
                            ResultSet r = null;
                            String usuario = request.getParameter("usuario");
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
                            String fecha1 = "";
                            int conta = 0;
                            String imgchida = "";
                            int imgmemo2 = 0;
                            try {
                                Class.forName("com.mysql.jdbc.Driver").newInstance();
                                con = DriverManager.getConnection("jdbc:mysql://localhost/MonolithV2", "root", "n0m3l0");
                                sta = con.createStatement();
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
                                    imgmemo2 = Integer.parseInt(r.getString("ImgdogoDa"));
                                    if (imgmemo2 < 10) {
                                        imgchida = "0" + imgmemo2;
                                    } else {
                                        imgchida = "" + imgmemo2;
                                    }
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
                                    <form class="col-6" method="post" name="Aceptar"  action="Interacep.jsp?id=<%=IDinter%>&usuario=<%=usuario%>">
                                        <button type="submit" class="btn btn-primary"  >Aceptar</button>
                                    </form>
                                    <form class="col-6" method="post" name="Negar"  action="negInter.jsp?id=<%=IDinter%>&usuario=<%=usuario%>">
                                        <button type="submit" class="btn btn-danger">Negar</button>
                                    </form>
                                </div>

                            </div>

                            <div id="collapse<%=conta%>" class="collapse" role="tabpanel" aria-labelledby="heading<%=conta%>">
                                <div class="card-body">
                                    <p id="fecha" name="fecha"><%=fecha1%></p>
                                    <p id="dogoda" name="dogoda">Te ofrece el <%=dogoda%></p>
                                    <p id="dogore" name="dogoda"> por el dogo# <%=dogore%></p>
                                    <p id="estado" name="estado">Estado: <%=estado%>

                                </div>
                                <div class="col-4">
                                    <img src="../img/dogo<%=imgchida%>.jpg" class="card-img-top img-fluid <%=filtroDa%>" alt="" >



                                </div>
                            </div>
                        </div>
                        <%}
                            } catch (SQLException error) {
                                out.print(error.toString());
                            }
                            if(conta==0){%>
                            <div class="col-12">
                                <h3>No hay ofertas</h3>
                                <img src="../img/Doggo_1.jpg">
                            </div>
                            <%}
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
