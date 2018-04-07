package Seguridad;

public class Boleto2 {

    private String Nombreusaurio;
    private String IDUsuario;
    private String IPUsuario;
    private String Servicio;
    private String NombreKDC;
    private String LlaveSesion;

    public String getNombreusaurio() {
        return Nombreusaurio;
    }

    public void setNombreusaurio(String Nombreusaurio) {
        this.Nombreusaurio = Nombreusaurio;
    }

    public String getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(String IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getIPUsuario() {
        return IPUsuario;
    }

    public void setIPUsuario(String IPUsuario) {
        this.IPUsuario = IPUsuario;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    public String getNombreKDC() {
        return NombreKDC;
    }

    public void setNombreKDC(String NombreKDC) {
        this.NombreKDC = NombreKDC;
    }

    public String getLlaveSesion() {
        return LlaveSesion;
    }

    public String CrearLlave() {
        String LLaveSesionReal = "";
        for (int i = 0; i < 15; i++) {
            LLaveSesionReal += Double.toString(Math.random());
        }
        return LLaveSesionReal;
    }

    public void setLlaveSesion(String LlaveSesion) {
        this.LlaveSesion = LlaveSesion;
    }

}
