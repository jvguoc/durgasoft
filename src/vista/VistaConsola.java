package vista;

import controlador.ControladorPrincipal;
import modelo.Socio;
import modelo.Excursion;
import java.util.Date;
import java.util.Scanner;

public class VistaConsola {
    private Scanner scanner = new Scanner(System.in);

    public void iniciarMenu(ControladorPrincipal ctrl) throws Exception {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar Socio\n2. Registrar Excursión\n3. Inscribir Socio\n4. Listar Socios\n5. Salir");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    System.out.print("Nombre: "); String nom = scanner.nextLine();
                    System.out.print("Localidad: "); String loc = scanner.nextLine();
                    System.out.print("Teléfono: "); String tel = scanner.nextLine();
                    ctrl.getControladorSocio().registrarSocio(new Socio(nom, loc, tel));
                    System.out.println("Socio registrado.");
                    break;
                case "2":
                    System.out.print("Nombre excursión: "); String ne = scanner.nextLine();
                    System.out.print("Lugar: "); String lu = scanner.nextLine();
                    Date fe = new Date();
                    System.out.print("Plazas: "); int pl = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorExcursion().registrarExcursion(new Excursion(ne, fe, lu, pl));
                    System.out.println("Excursión registrada.");
                    break;
                case "3":
                    System.out.print("ID Socio: "); int is = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID Excursión: "); int ie = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorInscripcion().inscribir(is, ie);
                    System.out.println("Inscripción completada.");
                    break;
                case "4":
                    ctrl.getControladorSocio().obtenerSocios().forEach(System.out::println);
                    break;
                case "5": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }
}