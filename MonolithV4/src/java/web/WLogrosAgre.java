/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import BaseDatos.Database2;
import Objetos.Logro;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alumno
 */
@WebService(serviceName = "WLogrosAgre")
public class WLogrosAgre {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "agregarlogros")
    public String agregarlogros(@WebParam(name = "nombre") String Usuario) {
        String salida = "";
        Logro l = new Logro();

        try {
            Database2 db = new Database2();
            int IDusuario = db.IDusu(Usuario);
            Random rand = new Random();
            int n = rand.nextInt(100) + 1;
            int img = rand.nextInt(14) + 1;
            int fil = rand.nextInt(10) + 1;
            Calendar fechita = new GregorianCalendar();
            int año = fechita.get(Calendar.YEAR);
            int mes = fechita.get(Calendar.MONTH);
            int dia = fechita.get(Calendar.DAY_OF_MONTH);
            int meschido = mes + 1;
            String fecha1 = "" + año + "-" + meschido + "-" + dia;
            int costo1 = 0;
            l.setIDusuario(IDusuario);
            l.setN(n);
            l.setImg(img);
            l.setFil(fil);
            l.setFecha(fecha1);
            if (db.Agregardogo(l, Usuario)) {
                salida="Logro agregado";
            } else {
                salida="error al agregar dogo";
            }
        } catch (Exception ex) {
            salida="Error en la base de datos";
        }
        return salida;
    }
}
