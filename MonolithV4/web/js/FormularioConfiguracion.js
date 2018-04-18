$(document).ready(inicio);

function inicio() {
    $.validator.addMethod("letras", function (value, element) {
        return this.optional(element) || /^[a-z]+$/i.test(value);
    }, "Ponga letras solamente");

    $("#Cambio").validate({
        rules: {
            txtNombreUsuario: {
                required: true,
                minlength:8,
                maxlength:15
            },
            txtEdadUsuario:{
               required: true,
                digits: true,
                maxlength:3
            }, txtPaisUsuario: {
                required: true,
                letras:true,
                minlength:3,
                maxlength:30
            },txtDirecUsuario:{
               required: true,
                minlength:12,
                maxlength:40
            },txtCorreo:{
                required: true,
                email: true
            },txtContra:{
                required: true,
                minlength:3,
                maxlength:20
            }
        },
        messages: {
           txtNombreUsuario: {
                required: "Llene el campo",
                minlength:"Ingrese un nombre de usuario de 8 caracteres en adelante",
                maxlength:"Ingrese un nombre de usuario de menos de 15 caracteres en adelante"
            },
            txtEdadUsuario:{
              required: "Llene el campo",
              digits: "Ponga un numero valido",
              maxlength:"Ingrese una edad valida"
            }, txtPaisUsuario: {
                 required: "Llene el campo",
                minlength:"Ingrese un pais valido",
                maxlength:"Ingrese un pais valido"
            },txtDirecUsuario:{
                required: "Llene el campo",
                minlength:"Ingrese una direccón valida",
                maxlength:"Ingrese una direccion valida"
            },txtCorreo:{
                required: "Llene el campo",
                email: "De un correo valido"
            },txtContra:{
                 required: "Llene el campo",
                minlength:"Ingrese una contraseña de al menos 8 caracteres",
                maxlength:"Ingrese una contraseña de menos 20 caracteres"
            }
        }
    });
}
;

