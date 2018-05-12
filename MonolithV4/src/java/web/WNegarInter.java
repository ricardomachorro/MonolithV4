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
@WebService(serviceName = "WNegarInter")
public class WNegarInter {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "NegarInter")
    public String NegarInter(@WebParam(name = "Id") String IDinter1) {
        String mensaje="";
        int IDInter = Integer.parseInt(IDinter1);
            Intercambio me = new Intercambio();

            Calendar fechita = new GregorianCalendar();
            int año = fechita.get(Calendar.YEAR);
            int mes = fechita.get(Calendar.MONTH);
            int dia = fechita.get(Calendar.DAY_OF_MONTH);
            int meschido = mes + 1;
            String fecha1 = "" + año + "-" + meschido + "-" + dia;
            me.setIDInter(IDInter);
            me.setFecha(fecha1);
            try {
                Database2 db = new Database2();
                db.NegarIntercambio(me);
                mensaje="Intercambio negado";
            } catch (Exception ex) {
                mensaje="Error en la base de datos";
            }
        return mensaje;
    }
}
