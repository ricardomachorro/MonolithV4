/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import BaseDatos.Database2;
import Objetos.Intercambio;
import Objetos.Logro;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alumno
 */
@WebService(serviceName = "WInter")
public class WInter {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "InterAgre")
    public String InterAgre(@WebParam(name = "usuario") String usuario,
            @WebParam(name = "numDa")String numDa,
            @WebParam(name = "usuRe")String UsuRe,
            @WebParam(name = "numRe")String numRe1,
            @WebParam(name = "IDimg")String IDimg1,
            @WebParam(name = "filtro")String filtro) {
        String mensaje = "";
        Logro l = new Logro();
        int IDusuarioDa = 0;
        int IDusuarioRe = 0;
        int IDimg = 0;
        int numRe = 0;
        Intercambio me = new Intercambio();
        try {
            numRe = Integer.parseInt(numRe1);
            IDimg = Integer.parseInt(IDimg1);
            Calendar fechita = new GregorianCalendar();
            int año = fechita.get(Calendar.YEAR);
            int mes = fechita.get(Calendar.MONTH);
            int dia = fechita.get(Calendar.DAY_OF_MONTH);
            int meschido = mes + 1;
            String fecha1 = "" + año + "-" + meschido + "-" + dia;
            String estado = "proceso";
            String chido = "";

            me.setDogoDa(numDa);
            me.setUsuarioRe(UsuRe);
            me.setDogoRe(numRe);
            me.setIdimg(IDimg);
            me.setFiltroDa(filtro);
            me.setFecha(fecha1);
            me.setEstado(estado);

            int img1 = 0;
            if (usuario.equals(UsuRe)) {

                mensaje="usuario no valido";

            } else {
                try {
                    Database2 db = new Database2();
                    IDusuarioDa = db.IDusu(usuario);
                    me.setIDusuarioDa(IDusuarioDa);
                    me.setUsuarioDa(usuario);
                    db.Intercambio(me);
                    mensaje="Oferta realizada con exito";
                } catch (Exception ex) {
                    mensaje="Error en la base de datos";
                }
            }
        } catch (java.lang.NumberFormatException e) {
            mensaje="Dogo no seleccionado";
        }
        return mensaje;
    }
}
