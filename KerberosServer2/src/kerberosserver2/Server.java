/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerberosserver2;

import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Date;
import BaseDatos.Database;
import Seguridad.*;


public class Server {
    
    
    ServerSocket server;

    String NombreKCD = "KCDMON";
    static String ClaveKCD = "5IM6";
    static String ClaveServicioServer = "Server";
    Socket socket;
    int puerto = 3000;
    ObjectInputStream entrada;
    ObjectOutputStream salida;
    String usuario = "root";
    String driver = "com.mysql.jdbc.Driver";
    String ruta = "jdbc:mysql://localhost/KerberosReal";
    String clave = "n0m3l0";
    Connection c = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    
    public void conexion() throws Exception {
        try{
            server = new ServerSocket(puerto);
           
            while (true) {
            socket = server.accept();
                entrada = new ObjectInputStream(socket.getInputStream());
                salida = new ObjectOutputStream(socket.getOutputStream());
                String opccion = entrada.readUTF();
                if (opccion.equalsIgnoreCase("1")) {
                }else if(opccion.equalsIgnoreCase("2")){
                    
                    String NombreUsuario = entrada.readUTF();
                    int IDUsuario = entrada.readInt();
                    String Servicio = entrada.readUTF();
                    String IPUsuario = entrada.readUTF();
                    Database db=new Database();
                    if(db.IdentificarUsuario(NombreUsuario, IDUsuario)){
                        Boleto2 ticket2=new Boleto2();
                        Boleto2 ticket3=new Boleto2();
                        Des des1=new Des(ClaveKCD);
                        
                        ticket2.setNombreusaurio(des1.Cifrar(NombreUsuario));
                        ticket2.setIDUsuario(des1.Cifrar(Integer.toString(IDUsuario)));
                        ticket2.setIPUsuario(des1.Cifrar(IPUsuario));
                        ticket2.setServicio(des1.Cifrar(Servicio));
                        ticket2.setNombreKDC(des1.Cifrar(NombreKCD));
                        ticket2.setLlaveSesion(des1.Cifrar(ticket2.CrearLlave()));
                         String LlaveSesion=ticket2.getLlaveSesion();
                         Des des2=new Des(Integer.toString(IDUsuario));
                        ticket3.setNombreusaurio(des2.Cifrar(ticket2.getNombreusaurio()));
                        ticket3.setIDUsuario(des2.Cifrar(ticket2.getIDUsuario()));
                        ticket3.setIPUsuario(des2.Cifrar(ticket2.getIPUsuario()));
                        ticket3.setServicio(des2.Cifrar(ticket2.getServicio()));
                        ticket3.setNombreKDC(des2.Cifrar(ticket2.getServicio()));
                        ticket3.setLlaveSesion(des2.Cifrar(ticket2.getLlaveSesion()));
                        
                        salida.writeBoolean(true);
                        salida.writeUTF(ticket3.getNombreusaurio());
                        salida.writeUTF(ticket3.getIDUsuario());
                        salida.writeUTF(ticket3.getIPUsuario());
                        salida.writeUTF(ticket3.getServicio());
                        salida.writeUTF(ticket3.getNombreKDC());
                        salida.writeUTF(ticket3.getLlaveSesion());
                        salida.writeUTF(des2.Cifrar(LlaveSesion));
                    }
                    
                }
            }
            
        }catch(Exception ex){
            
        }
    }
    
}
