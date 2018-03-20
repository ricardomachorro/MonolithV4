
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;
import javax.xml.bind.DatatypeConverter;
import Seguridad.*;

public class ServerCliente {
    //Parametros a cambiar===
    static final String HOST1 = "192.168.0.4"; //Parametro para el servidor de el proyecto de KerberosServer
    static final int PUERTO1 = 3000;
    
    static final String HOST2 = "192.168.0.4";//Parametro para el servidor de el proyecto de PermisosServer
    static final int PUERTO2 = 4000;
    //===
    ObjectOutputStream alServidor;
    ObjectInputStream delServidor;
    ObjectOutputStream alServidor2;
    ObjectInputStream delServidor2;

    public boolean EnviarRegistroUsuario(String NombreUsuario, String Password, String IDMaquinaCliente) {
         boolean RegistroExitoso=false;
        try {
            Socket skKDC = new Socket(HOST1, PUERTO1);
            alServidor = new ObjectOutputStream(skKDC.getOutputStream());
            delServidor = new ObjectInputStream(skKDC.getInputStream());
            alServidor.writeUTF("1");
            alServidor.flush();
            alServidor.writeUTF(NombreUsuario);
            alServidor.flush();
            alServidor.writeUTF(Password);
            alServidor.flush();
            alServidor.writeUTF(IDMaquinaCliente);
            alServidor.flush();
            RegistroExitoso=delServidor.readBoolean();

        } catch (Exception ex) {
           
        }
       return RegistroExitoso;
    }

    public boolean EnviarPeticionUsuario(String NombreUsuario, String Nombrecifrado, String IDMaquinaUsuario, String ContraUsuario) {
        boolean finalbol = false;
        try {
            finalbol= false;
            Socket skKDC = new Socket(HOST1, PUERTO1);
            alServidor = new ObjectOutputStream(skKDC.getOutputStream());
            delServidor = new ObjectInputStream(skKDC.getInputStream());
            alServidor.writeUTF("2");
            alServidor.flush();
            alServidor.writeUTF(NombreUsuario);
            alServidor.flush();
            alServidor.writeUTF(Nombrecifrado);
            alServidor.flush();
            alServidor.writeUTF(IDMaquinaUsuario);
            alServidor.flush();
            boolean confirmacion1 = delServidor.readBoolean();
            boolean confirmacion2 = delServidor.readBoolean();
           
            if (confirmacion1 && confirmacion2) {
                Des des = new Des();
                HexEstandar hex = new HexEstandar();
                String userCipher = hex.StringToHex(ContraUsuario);
                ArrayList<String> NombreusaurioArray = (ArrayList<String>) delServidor.readObject();
                ArrayList<String> NombreKDCArray = (ArrayList<String>) delServidor.readObject();
                ArrayList<String> IPUserArray = (ArrayList<String>) delServidor.readObject();
                String llaveSesion = delServidor.readUTF();
                for (int i = 0; i < NombreusaurioArray.size(); i++) {
                    String DatoADescifrar = NombreusaurioArray.get(i);
                    String DatoDescifrado = des.DesCifrado(DatoADescifrar, userCipher);
                    NombreusaurioArray.set(i, DatoDescifrado);
                }
                for (int i = 0; i < NombreKDCArray.size(); i++) {
                    String DatoADescifrar = NombreKDCArray.get(i);
                    String DatoDescifrado = des.DesCifrado(DatoADescifrar, userCipher);
                    NombreKDCArray.set(i, DatoDescifrado);
                }
                for (int i = 0; i < IPUserArray.size(); i++) {
                    String DatoADescifrar = IPUserArray.get(i);
                    String DatoDescifrado = des.DesCifrado(DatoADescifrar, userCipher);
                    IPUserArray.set(i, DatoDescifrado);
                }

                String LlaveSesionDescifrada = des.DesCifrado(llaveSesion, userCipher);

                InetAddress address = InetAddress.getLocalHost();
                Date fecha = new Date();

                ConfirmacionSesion confirsecion = new ConfirmacionSesion();

                ArrayList<String> UsuarioSecionArray = confirsecion.CreacionArrays(NombreUsuario, LlaveSesionDescifrada);
                ArrayList<String> IPUsuarioSecionArray = confirsecion.CreacionArrays(address.getHostAddress(), LlaveSesionDescifrada);

                alServidor.writeObject(NombreusaurioArray);
                alServidor.writeObject(NombreKDCArray);
                alServidor.writeObject(IPUserArray);
                alServidor.writeObject(UsuarioSecionArray);
                alServidor.writeObject(IPUsuarioSecionArray);
                alServidor.writeObject(fecha);
                alServidor.writeUTF("Ingreso");
                alServidor.flush();

                boolean confirmacion3 = delServidor.readBoolean();
                if (confirmacion3) {
                    ArrayList<String> UsuarioTicketServicio = (ArrayList<String>) delServidor.readObject();
                    ArrayList<String> ServicioTicketServicio = (ArrayList<String>) delServidor.readObject();
                    ArrayList<String> IPUsuarioTicketServicio = (ArrayList<String>) delServidor.readObject();
                    String llaveServicioCifradoConServer = delServidor.readUTF();
                    String llaveServicioCifradoConServico = delServidor.readUTF();

                    for (int i = 0; i < UsuarioTicketServicio.size(); i++) {
                        String DatoADescifrar = UsuarioTicketServicio.get(i);
                        String DatoDescifrado = des.DesCifrado(DatoADescifrar, LlaveSesionDescifrada);
                        UsuarioTicketServicio.set(i, DatoDescifrado);
                    }

                    for (int i = 0; i < ServicioTicketServicio.size(); i++) {
                        String DatoADescifrar = ServicioTicketServicio.get(i);
                        String DatoDescifrado = des.DesCifrado(DatoADescifrar, LlaveSesionDescifrada);
                        ServicioTicketServicio.set(i, DatoDescifrado);
                    }

                    for (int i = 0; i < IPUsuarioTicketServicio.size(); i++) {
                        String DatoADescifrar = IPUsuarioTicketServicio.get(i);
                        String DatoDescifrado = des.DesCifrado(DatoADescifrar, LlaveSesionDescifrada);
                        IPUsuarioTicketServicio.set(i, DatoDescifrado);
                    }

                    String LlaveServicioTicket = des.DesCifrado(llaveServicioCifradoConServer, LlaveSesionDescifrada);
                    String LlaveServicioDescifrada = des.DesCifrado(llaveServicioCifradoConServico, LlaveSesionDescifrada);

                    ArrayList<String> UsuarioServicioArray = confirsecion.CreacionArrays(NombreUsuario, LlaveServicioDescifrada);
                    ArrayList<String> ServicioUserServicio = confirsecion.CreacionArrays("Ingreso", LlaveServicioDescifrada);
                    ArrayList<String> IPUsuarioServicioArray = confirsecion.CreacionArrays(address.getHostAddress(), LlaveServicioDescifrada);

                    Socket skServicio = new Socket(HOST2, PUERTO2);
                    alServidor2 = new ObjectOutputStream(skServicio.getOutputStream());
                    delServidor2 = new ObjectInputStream(skServicio.getInputStream());
                    alServidor2.writeUTF("1");
                    alServidor2.flush();
                    alServidor2.writeObject(UsuarioTicketServicio);
                    alServidor2.writeObject(ServicioTicketServicio);
                    alServidor2.writeObject(IPUsuarioTicketServicio);
                    alServidor2.writeUTF(LlaveServicioTicket);
                    alServidor2.flush();
                    alServidor2.writeObject(UsuarioServicioArray);
                    alServidor2.writeObject(ServicioUserServicio);
                    alServidor2.writeObject(IPUsuarioServicioArray);
                    finalbol=delServidor2.readBoolean();
                   skServicio.close();
                } 

            } 
        } catch (Exception ex) {
                  
        }
        return finalbol;
    }
}
