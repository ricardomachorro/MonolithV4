/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Des {
    
    private SecretKey key;
    
     public void Des( String Llave) throws Exception{
        DESKeySpec desKeySpec=new DESKeySpec(Llave.getBytes());
        SecretKeyFactory keyFactory= SecretKeyFactory.getInstance("DES");
        key=keyFactory.generateSecret(desKeySpec);
    }
     
     private String Cifrar(String Mensaje) throws Exception{
       String MensajeCifrado="";
       Cipher cifrador=Cipher.getInstance("DES");
       cifrador.init(Cipher.ENCRYPT_MODE,key);
       byte[] MensajeBytes=Mensaje.getBytes();
       byte [] MensajeCifradoBytes=cifrador.doFinal(MensajeBytes);
       BASE64Encoder encoder=new BASE64Encoder();
       MensajeCifrado=encoder.encode(MensajeCifradoBytes);
       return MensajeCifrado;
    }
    
    private String Descifrar(String MensajeCifrado) throws Exception{
        String MensajeDescifrado="";
        Cipher descifrador=Cipher.getInstance("DES");
        descifrador.init(Cipher.DECRYPT_MODE, key);
        BASE64Decoder decoder=new BASE64Decoder();
         byte [] MensajeCifradoBytes=decoder.decodeBuffer(MensajeCifrado);
         byte [] MensajeDesCifradoBytes=descifrador.doFinal(MensajeCifradoBytes);
         MensajeDescifrado=new String(MensajeDesCifradoBytes,"UTF");
        return MensajeDescifrado;
    }
    
}
