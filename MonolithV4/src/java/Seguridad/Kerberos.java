/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;
import javax.xml.bind.DatatypeConverter;

public class Kerberos {

    static final String HOST1 = "192.168.0.5";
    static final int PUERTO1 = 5000;
    static final String HOST2 = "192.168.0.5";
    static final int PUERTO2 = 4100;
    ObjectOutputStream alServidor;
    ObjectInputStream delServidor;
    ObjectOutputStream alServidor2;
    ObjectInputStream delServidor2;

    public boolean EnviarPeticionUsuario(Boleto1 ticket) {
        boolean exito = false;

        try {
            Socket skKDC = new Socket(HOST1, PUERTO1);
            alServidor = new ObjectOutputStream(skKDC.getOutputStream());
            delServidor = new ObjectInputStream(skKDC.getInputStream());
            alServidor.writeUTF("2");
            alServidor.flush();
            alServidor.writeUTF(ticket.getNombreUsuario());
            alServidor.flush();
            alServidor.writeInt(ticket.getIDentificadorUsuario());
             alServidor.flush();
            alServidor.writeUTF(ticket.getNombreServicio());
             alServidor.flush();
             alServidor.writeUTF(ticket.getIPUsaurio());
             alServidor.flush();
             boolean confirmacion1=delServidor.readBoolean();
             if(confirmacion1){
                 Des des1=new Des(Integer.toString(ticket.getIDentificadorUsuario()));
                 
             }
             
        } catch (Exception ex) {

        }

        return exito;
    }

}
