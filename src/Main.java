import controlador.ControladorPrincipal;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        ControladorPrincipal controlador = new ControladorPrincipal();
        VistaConsola vista = new VistaConsola();
        vista.iniciarMenu(controlador);
    }
}
