import controlador.ControladorPrincipal;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        try {
            ControladorPrincipal ctrl = new ControladorPrincipal();
            new VistaConsola().iniciarMenu(ctrl);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
