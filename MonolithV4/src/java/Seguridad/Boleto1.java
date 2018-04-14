
package Seguridad;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Boleto1 {
    
    private String NombreUsuario;
    private String NombreServicio;
    private String IPUsaurio;
    private int IDentificadorUsuario;

    public int getIDentificadorUsuario() {
        return IDentificadorUsuario;
    }

    public void setIDentificadorUsuario(int IDentificadorUsuario) {
        this.IDentificadorUsuario = IDentificadorUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getNombreServicio() {
        return NombreServicio;
    }

    public void setNombreServicio(String NombreServicio) {
        this.NombreServicio = NombreServicio;
    }

    public String getIPUsaurio() {
        return IPUsaurio;
    }

    public void setIPUsaurio(String IPUsaurio) throws UnknownHostException {
        this.IPUsaurio = InetAddress.getLocalHost().getHostAddress();
    }

  
}
