/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;
import javax.crypto.*;
import com.sun.crypto.provider.DESKeyFactory;
import java.security.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Des {
     KeyGenerator GeneradorKeys;
     SecretKey Llave;
     Cipher Cifrado;
     Cipher DesCifrado;
     
     private void Des()throws Exception{
         GeneradorKeys=KeyGenerator.getInstance("DES");
         Llave=GeneradorKeys.generateKey();
         Cifrado=Cipher.getInstance("DES");
         DesCifrado=Cipher.getInstance("DES");
         Cifrado.init(Cipher.ENCRYPT_MODE, Llave);
         DesCifrado.init(Cipher.DECRYPT_MODE,Llave);
     }
     
     public String Cifrar(String Mensaje) throws Exception{
         String MensajeCifrado="";
         byte[] utf8 = Mensaje.getBytes("UTF8");
         byte[] enc = Cifrado.doFinal(utf8);
         return new sun.misc.BASE64Encoder().encode(enc);
     }
     
     public String DesCifrar(String MensajeCifrado) throws Exception{
         byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(MensajeCifrado);
         byte[] utf8 = DesCifrado.doFinal(dec);
         return new String(utf8, "UTF8");
     }
    
}
