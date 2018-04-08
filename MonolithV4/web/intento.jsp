<!DOCTYPE html>
<html>
    <head>
        <title>Modificacion de productos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
        <link rel="stylesheet" href="Css/bootstrap.min.css">
    </head>

    <body style="background-color:#eaeef2;">
        <header>
            <div class="navbar navbar-dark bg-dark box-shadow">
                <div class="container d-flex justify-content-between">
                    <a href="#" class="navbar-brand d-flex align-items-center">
                        <!--Foto-->
                        <strong>Modificacion de producto</strong>
                    </a>
                </div>
            </div>
        </header>
        <div class="container">
            <div class="row mt-3">
                <div class="col-12 justify-content-center">
                    <form action="" class="form-inline">
                        <input type="text" class="form-control mr-3" name="correo" id="nombre" placeholder="nombre de producto">

                        <input type="submit" class="btn btn-success" value="Buscar">
                    </form>
                    <button class="btn btn-secondary" type="submit" onclick="regre()">Regresar</button>
                </div>

                <div class="col-auto justify-content-center">
                    <div class="card">
                        <img src="Assets/coca.jpg" class="card-img-top img-fluid" alt="">
                        <div class="card-body">
                            <h3 class="card-title">Shiba</h3>
                            <p class="card-text">Administrador de la tienda</p>
                            <button class="btn btn-secondary" data-toggle="modal" data-target="#fm-modal">Modificar</button>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="fm-modal" tabindex="-1" role="dialog" aria-labelledby="fm-modal" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="">Modificacion de datos de producto</h5>
                                <button class="close" data-dismiss="modal" aria-label="Cerrar">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div class="modal-body">
                                <form method="post" action="ingreso.html">
                                    <input type="text" placeholder="Ingresa el nombre del producto" size="50" required/><br><br>
                                    <input type="text" placeholder="Ingresa la marca del producto" size="50" required/><br><br>
                                    Ingresa la fecha de caducidad<br>
                                    <input type="date"placeholder = "Ingresa la fecha de caducidad" required/><br>
                                    <form>
                                        Seleccione en que tipo de envase viene<br>
                                        Litros<input type="radio" value="litros" name="envase" checked/> <br>
                                        Kilos<input type="radio" value="kilogramos" name="envase"><br>
                                        Galones <input type="radio" value="Galones" name="envase"><br>
                                        Gramos <input type="radio" value="gramos" name="envase"><br>
                                        Piezas <input type="radio" value="piezas" name="envase"><br>
                                        Mililitros <input type="radio" value="mili" name="envase"><br>
                                        <input type="text" placeholder="Ingrese la cantidad del producto" size="50" required/><br><br>
                                        <input type="text" placeholder="Ingrese el nombre del proveedor" size="50" required/><br><br>


                                    </form>
                                    <button class="btn btn-secondary" type="button" onclick="regi()">Registrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function regre() {
                window.location = "Inventario.html";
            }
            function regi() {
                alert("producto modificaso");
                window.location = "cambios.html";
            }
        </script>
        <script src="JS/jquery-3.2.1.min.js"></script>
        <script src="JS/popper.min.js"></script>
        <script src="JS/bootstrap.min.js"></script>
    </body>
</html>