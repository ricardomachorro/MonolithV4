/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Kerberos {
    
    //Parametros a cambiar===
    static final String HOST1 = "192.168.0.4"; //Parametro para el servidor de el proyecto de KerberosServer
    static final int PUERTO1 = 3000;
    
    static final String HOST2 = "192.168.0.4";//Parametro para el servidor de el proyecto de PermisosServer
    static final int PUERTO2 = 4000;
    
    ObjectOutputStream alServidor;
    ObjectInputStream delServidor;
    ObjectOutputStream alServidor2;
    ObjectInputStream delServidor2;
    
    
}
