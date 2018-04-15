//Array de miembros de un nuevo grupo
var miembrosNG = "";
var nomGrupo = "";
var contadorTarea = 1;

/*FUNCION PARA EL EVENTO DE AGREGAR UN MIEMBRO NUEVO A UN GRUPO NUEVO*/
$("#agregarMiembro").click(//Agregar miembro al formulario para el nuevo grupo
        function () {
            var nuevoMiembro = $("#miembro").val().toString();
            nomGrupo = $("#nuevoGrupo").val().toString();
            $.ajax({
                url: "AgregarMiembro",
                data: {
                    correoMiembro: nuevoMiembro
                },
                type: 'post',
                success: function (data) {//data trae el nombre del usuario
                    $("#ListaMiembros").prepend(//Para insertar html
                            $(//Todo el nuevo contenido
                                    "<li class='list-group-item d-flex justify-content-between align-items-center'>" +
                                    data.toString() + //El nombre del miembro alv
                                    "<span class='badge'>" +
                                    "<button type='button' class='close' aria-label='Close'>" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button>" +
                                    "</span>" +
                                    "</li>"
                                    )
                            );
                    if (miembrosNG.length !== 0) {
                        miembrosNG = miembrosNG + "," + data.toString();//Agregar el nombre del miembro al array
                    } else {
                        miembrosNG = data.toString();
                    }
                    //$("#nuevoGrupo").val(nomGrupo);
                },
                error: function () {
                    alert("Error buscando miembro");
                },
                complete: function () {
                }
            });
        }
);

/*FUNCION PARA EL EVENTO DE CREAR UN GRUPO NUEVO*/
$("#btnCrearGrupo").click(
        function () {
            var nuevoGrupo = $("#nuevoGrupo").val().toString();//Traigo el nombre del grupo
            var usuario = $("#btnCrearGrupo").val().toString();//Traigo el nombre del creado alv
            var miembrosLista = miembrosNG;//inserto el valor de la lista de miembros
            if (miembrosLista.length !== 0) {//Evaluo que se halla agregado almenos un miembro
                $.ajax({
                    url: "CrearGrupos",
                    data: {//Envio el nombre del grupo y los miembros
                        nomNuevoGrupo: nuevoGrupo,
                        lider: usuario,
                        miembros: miembrosLista
                    },
                    type: 'post',
                    success: function () {
                        //Desaparecer elementos
                        $("div.grupo").removeClass("active");
                        $("a.grupoLista").removeClass("active");
                        //LADO DERECHO
                        $("#ContenidoGrupos").prepend(//Inserto a tarjeta que contiene al nuevo grupo
                                "<div class='tab-pane fade show grupo active' id='panel-g" + nuevoGrupo + "' role='tabpanel' aria-labelledby='lista-g" + nuevoGrupo + "'>" +
                                //Inicio titulo contenedor
                                "<div class='card-deck'>" +
                                "<div class='card Contenedor'>" +
                                "<div class='card-body'>" +
                                "<div class='row'>" +
                                "<div class='col d-flex align-items-center justify-content-center'>" +
                                nuevoGrupo +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                //Fin titulo contenedor
                                //Inicio cuerpo contenedor
                                "<div class='card-deck'>" +
                                "<div class='card'>" +
                                "<div class='card-body'>" +
                                //Inicio agregar tarea
                                "<form>" +
                                "<div class='form-row align-items-center'>" +
                                "<div class='col-sm-5 mt-2'>" +
                                "<input type='text' class='form-control' placeholder='Ingresa una tarea'>" +
                                "</div>" +
                                "<div class='col-sm-5 mt-2'>" +
                                "<div class='input-group'>" +
                                "<div class='input-group-prepend'>" +
                                "<div class='input-group-text'>@</div>" +
                                "</div>" +
                                "<input type='text' class='form-control' placeholder='Correo'>" +
                                "</div>" +
                                "</div>" +
                                "<div class='col-sm-2 d-flex justify-content-center mt-2'>" +
                                "<button type='submit' class='btn btn-primary'>Agregar</button>" +
                                "</div>" +
                                "</div>" +
                                "</form>" +
                                //Fin agregar tarea
                                //Inicio contenedor de lista de tareas
                                "<div class='row rowListaTareas'>" +
                                //Inicio lista de tareas
                                "<div class='col-12' id='listaTareas-" + nuevoGrupo + "'" +
                                "</div>" +
                                //Fin lista de tareas
                                "</div>" +
                                //Fin contenedor de lista de tareas
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                //Fin cuerpo contenedor
                                "</div>"
                                );
                        //LADO IZQUIERDO
                        $("#list-tab").prepend(//Inserto el grupo a la lista de grupos
                                "<a class='list-group-item list-group-item-action active grupoLista' id='lista-g" + nuevoGrupo + "' data-toggle='list' href='#panel-g" + nuevoGrupo + "' role='tab' aria-controls='g" + nuevoGrupo + "'>" +
                                "<img src='img/group.svg' alt='ic_grupos'>" +
                                nuevoGrupo +
                                "</a>"
                                );
                        miembrosNG = "";
                    },
                    error: function () {
                        alert("Hubo un error :'v ");
                    },
                    complete: function () {
                    }
                });
            } else {
                alert("Agrega al menos un miembro a tu grupo");
                //$("#nuevoGrupo").val(nuevoGrupo);
            }
        }
);

/*FUNCION PARA EL EVENTO DE AGREGAR UNA TAREA*/
function agregarTarea(e) {
    var idT = e.id.toString();//id del elemento presionado (btn)  
    var nombreTarea = "";
    var miembroTarea = "";
    var idConcatenada = "";
    var objFecha;//Para armar la fecha
    var mes;
    var dia;
    var fechaTarea = "";//Para acumular el valor de fecha

//    Pruebas
     console.log("id btn: " + idT);
      console.log("id grupo: " + document.getElementById(idT).form.id.toString());
//      var idForm = document.getElementById(idT).form.id.toString(); //id del formulario donde esta el btn, el cual es el nombre del grupo
     
    $("#FormularioNuevaTarea"+idT).validate({//Validacion >:v
        rules: {
            txtNuevaTarea: {
                required: true
            }
        },
        messages: {
            txtNuevaTarea: {
                required: "Llene el Campo"
            }
        },
        submitHandler: function (form) {
            console.log("Iniciado...");
            //Para el nombre de la tarea
            nombreTarea = $("#txtNuevaTarea"+idT).val();
            console.log("Nombre tarea: " + nombreTarea);
            miembroTarea = $("#txtMiembro"+idT).val();
            console.log("Miembro. " + miembroTarea);
            idConcatenada = contadorTarea + nombreTarea;
            console.log("idConcatenada: " + idConcatenada);
            //Para la fecha
            objFecha = new Date();
            mes = objFecha.getMonth() + 1;//Un mes mas tarde
            dia = objFecha.getDay() + 1;//Un dia despues
            fechaTarea = objFecha.getFullYear() + "-" + mes + "-" + dia;
            console.log("fecha: " + fechaTarea);
            //Agregando tarea a la lista de tareas del grupo
            $("#listaTareas-" + idT).prepend(
                "<!--Inicio tarea-->"+
                "<div class='card mt-3'>"+
                    "<!--Inicio parte visible-->"+
                        "<div class='card-header' id='headTarea-"+idConcatenada+"'>"+
                            "<div class='row'>"+
                                "<!--Nombre tarea-->"+
                                "<div class='col-sm-3 d-flex align-items-center justify-content-center my-2'>"+
                                    "<h5 class='mb-0'>"+
                                        "<!--btn para hacer que se despliegue la parte invisible-->"+
                                        "<button class='btn btn-link p-0 collapsed' data-toggle='collapse'"+
                                        "data-target='#tarea-"+idConcatenada+"' aria-expanded='false'"+
                                        "aria-controls='tarea-"+idConcatenada+"'>"+
                                            "<span class='d-inline-block text-truncate' style='max-width: 150px;'>"+
                                                nombreTarea+
                                            "</span>"+
                                        "</button>"+
                                    "</h5>"+
                                "</div>"+
                                "<!--Fecha limite-->"+
                                "<div class='col-sm-3 d-flex align-items-center justify-content-center my-2'>"+
                                    "<small class='text-muted' id='fechaTarea-"+idConcatenada+"'>"+
                                        fechaTarea+
                                    "</small>"+
                                "</div>"+
                                "<!--Miembros-->"+
                                "<div class='col-sm-3 d-flex align-items-center justify-content-center'>"+
                                    "<div class='dropdown'>"+
                                        "<button class='btn btn-secondary dropdown-toggle' type='button'"+
                                            "id='menuMiembros-"+idConcatenada+"' data-toggle='dropdown'"+
                                            "aria-haspopup='true' aria-expanded='false'> Miembros"+
                                        "</button>"+
                                        "<div class='dropdown-menu' aria-labelledby='menuMiembros my-2'"+
                                            "id='dropdown-miembros-NombreGrupo'>"+
                                            "<button class='dropdown-item disabled' type='button'>"+miembroTarea+"</button>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>"+
                                "<!--Checkbox para marcar actividad-->"+
                                "<div class='col-sm-3 d-flex align-items-center justify-content-center my-2'>"+
                                    "<input class='acabarTarea float-right checado' type='checkbox'>"+
                                "</div>"+
                            "</div>"+
                        "</div>"+
                    "<!--Fin parte visible-->"+
                    "<!--Inicio parte colapsable-->"+
                        "<div id='tarea-"+idConcatenada+"' class='collapse' aria-labelledby='headTarea-"+idConcatenada+"' data-parent='#listaTareas-"+idT+"'>"+
                            "<div class='card-body'>"+
                                "<form>"+
                                    "<!--Inicio formulario para modificar actividad-->"+
                                    "<div class='form-row'>"+
                                        "<div class='col-sm-3'>"+
                                            "<input type='text' class='form-control' id='tarea' name='tarea' placeholder='Cambiar nombre tarea' value='"+nombreTarea+"'>"+
                                        "</div>"+
                                        "<div class='col-sm-3'>"+
                                            "<input type='text' class='form-control' id='fecha' name='fecha' placeholder='Cambiar nombre tarea' value='"+fechaTarea+"'>"+
                                        "</div>"+
                                        "<div class='col-sm-3'>"+
                                            "<input type='text' class='form-control' id='eliMiembro' name='eliMiembro' placeholder='Eliminar miembro asignado'>"+
                                        "</div>"+
                                        "<div class='col-sm-3'>"+
                                            "<input type='text' class='form-control' id='agrMiembro' name='agrMiembro' placeholder='Agregar miembro asignado'>"+
                                        "</div>"+
                                    "</div>"+
                                    "<!--Fin formulario para modificar actividad-->"+
                                    "<!--Inicio opciones-->"+
                                    "<div class='form-row mt-2'>"+
                                        "<div class='col-sm-6'>"+
                                            "<button type='button' class='btn btn-outline-warning mb-2 btnModificar' style='width: 100%;'>Modificar</button>"+
                                        "</div>"+
                                        "<div class='col-sm-6'>"+
                                            "<button type='button' class='btn btn-outline-danger mb-2 btnEliminar' style='width: 100%;'>Eliminar</button>"+
                                        "</div>"+
                                    "</div>"+
                                    "<!--Fin opciones-->"+
                                "</form>"+
                            "</div>"+
                        "</div>"+
                    "<!--Fin parte colapsable-->"+
                "</div>"+
                "<!--Fin tarea-->"
            );
        }
    });
    
    contadorTarea++;
}

