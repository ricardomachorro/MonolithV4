/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import BaseDatos.Database2;
import Objetos.Intercambio;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alumno
 */
@WebService(serviceName = "WAceptarInter")
public class WAceptarInter {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "aceptarinter")
    public String aceptarinter(@WebParam(name = "IDInter") String IDInter1,
            @WebParam(name = "IDdogoRe") String IDdogoRe1) {
        
        String mensaje="";
        int IDdodoDa = 0;
        int IDdogoRe = Integer.parseInt(IDdogoRe1);
        int IDInter = Integer.parseInt(IDInter1);
        int img1 = 0;
        int img2 = 0;
        int filtro1 = 0;
        int filtro2 = 0;
        String nombre1 = "";
        String nombre2 = "";
        Calendar fechita = new GregorianCalendar();
        int año = fechita.get(Calendar.YEAR);
        int mes = fechita.get(Calendar.MONTH);
        int dia = fechita.get(Calendar.DAY_OF_MONTH);
        int meschido = mes + 1;
        String fecha1 = "" + año + "-" + meschido + "-" + dia;

        Intercambio me = new Intercambio();
        me.setIDdogoRe(IDdogoRe);
        me.setIDInter(IDInter);
        me.setFecha(fecha1);

        try {

            Database2 db = new Database2();
            db.AcepInter(me);

            mensaje="Intercambio realizado";

        } catch (Exception ex) {
            mensaje="Error en la base de datos";
        }
        return mensaje;
    }
}
