/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import BaseDatos.Database2;
/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "Actividad")
public class Actividad {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "IngresarActividad")
    public void hello(@WebParam(name = "NombreActividad") String Nombre) {
        try{
            Database2 db=new Database2();
        }catch(Exception ex){
            
        }
        
        
    }
}
