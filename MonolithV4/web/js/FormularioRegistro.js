
$(document).ready(inicio);




function inicio() {
    $.validator.addMethod("letras", function (value, element) {
        return this.optional(element) || /^[a-z]+$/i.test(value);
    }, "Ponga letras solamente");

    $("#registro").validate({
        rules: {
            nombre: {
                required: true,
                minlength:8,
                maxlength:15
            },
            institucion: {
                required: true,
                minlength:10,
                maxlength:40
            },
            estudios: {
                required: true,
                minlength:7,
                maxlength:30
            },
            edad: {
                required: true,
                digits: true,
                maxlength:3
            },
            pais: {
                required: true,
                letras:true,
                minlength:3,
                maxlength:30
            },
            direccion: {
                required: true,
                minlength:12,
                maxlength:40
            },
            correo: {
                required: true,
                email: true
            },
            contra: {
                required: true,
                minlength:3,
                maxlength:20
                
            }
        },
        messages: {
            nombre: {
                required: "Llene el campo",
                minlength:"Ingrese un nombre de usuario de 8 caracteres en adelante",
                maxlength:"Ingrese un nombre de usuario de menos de 15 caracteres en adelante"
            },
            institucion: {
                required: "Llene el campo",
                minlength:"Ingrese una Institucion con almenos 10 caracteres",
                maxlength:"Ingrese una Institucion con menos  de 40 caracteres"
            },
            estudios: {
                required: "Llene el campo",
                minlength:"Ingrese un grado de estudios de almenos 7 caractes",
                maxlength:"Ingrese un grado de estudios de menos 30 caractes"
            },
            edad: {
                required: "Llene el campo",
                digits: "Ponga un numero valido",
                maxlength:"Ingrese una edad valida"
            },
            pais: {
                required: "Llene el campo",
                minlength:"Ingrese un pais valido",
                maxlength:"Ingrese un pais valido"
            },
            direccion: {
                required: "Llene el campo",
                minlength:"Ingrese una direccón valida",
                maxlength:"Ingrese una direccion valida"
            },
            correo: {
                required: "Llene el campo",
                email: "De un correo valido"
            },
            contra: {
                required: "Llene el campo",
                minlength:"Ingrese una contraseña de al menos 8 caracteres",
                maxlength:"Ingrese una contraseña de menos 20 caracteres"
            }
        }
    });
}
;











       