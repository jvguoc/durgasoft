package vista;

import controlador.ControladorPrincipal;
import modelo.Socio;
import modelo.Excursion;
import modelo.Inscripcion;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private Scanner scanner = new Scanner(System.in);
    public void iniciarMenu(ControladorPrincipal ctrl) throws Exception {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar Socio");
            System.out.println("2. Modificar Socio");
            System.out.println("3. Eliminar Socio");
            System.out.println("4. Listar Socios");
            System.out.println("5. Registrar Excursión");
            System.out.println("6. Modificar Excursión");
            System.out.println("7. Eliminar Excursión");
            System.out.println("8. Listar Excursiones");
            System.out.println("9. Inscribir Socio en Excursión");
            System.out.println("10. Eliminar Inscripción");
            System.out.println("11. Listar Inscripciones");
            System.out.println("12. Salir");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    System.out.print("Nombre: "); String nom = scanner.nextLine();
                    System.out.print("Localidad: "); String loc = scanner.nextLine();
                    System.out.print("Teléfono: "); String tel = scanner.nextLine();
                    ctrl.getControladorSocio().registrarSocio(new Socio(nom, loc, tel));
                    break;
                case "2":
                    System.out.print("ID: "); int idSM = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nombre: "); String nM = scanner.nextLine();
                    System.out.print("Localidad: "); String lM = scanner.nextLine();
                    System.out.print("Teléfono: "); String tM = scanner.nextLine();
                    ctrl.getControladorSocio().modificarSocio(new Socio(nM, lM, tM));
                    break;
                case "3":
                    System.out.print("ID: "); int idSD = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorSocio().eliminarSocio(idSD);
                    break;
                case "4":
                    ctrl.getControladorSocio().obtenerSocios().forEach(System.out::println);
                    break;
                case "5":
                    System.out.print("Nombre: "); String ne = scanner.nextLine();
                    System.out.print("Lugar: "); String lu = scanner.nextLine();
                    System.out.print("Fecha (dd/MM/yyyy): "); String fS = scanner.nextLine();
                    Date f;
                    try { f = new SimpleDateFormat("dd/MM/yyyy").parse(fS); } catch(Exception e){f=new Date();}
                    System.out.print("Plazas: "); int p = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorExcursion().registrarExcursion(new Excursion(ne, f, lu, p));
                    break;
                case "6":
                    System.out.print("ID: "); int idEM = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nombre: "); String nE = scanner.nextLine();
                    System.out.print("Lugar: "); String lE = scanner.nextLine();
                    System.out.print("Fecha (dd/MM/yyyy): "); String fM = scanner.nextLine();
                    Date f2;
                    try { f2 = new SimpleDateFormat("dd/MM/yyyy").parse(fM); } catch(Exception e){f2=new Date();}
                    System.out.print("Plazas: "); int p2 = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorExcursion().actualizarExcursion(new Excursion(nE,f2,lE,p2));
                    break;
                case "7":
                    System.out.print("ID: "); int idED = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorExcursion().eliminarExcursion(idED);
                    break;
                case "8":
                    List<Excursion> listaExc = ctrl.getControladorExcursion().obtenerExcursiones();
                    if (listaExc.isEmpty()) {
                        System.out.println("No hay excursiones registradas.");
                    } else {
                        listaExc.forEach(System.out::println);
                    }
                    break;
                case "9":
                    System.out.print("ID Socio: "); int is = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID Excursión: "); int ie = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorInscripcion().inscribir(is, ie);
                    break;
                case "10":
                    System.out.print("ID Inscr: "); int iid = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorInscripcion().eliminarInscripcion(iid);
                    break;
                case "11":
                    ctrl.getControladorInscripcion().obtenerInscripciones()
                            .forEach(ins -> System.out.println(
                                    "Inscripción " + ins.getIdInscripcion() + ": Socio "
                                            + ins.getSocio().getIdSocio() + ", Excursión "
                                            + ins.getExcursion().getIdExcursion() + ", Fecha "
                                            + new SimpleDateFormat("dd/MM/yyyy").format(ins.getFechaInscripcion())
                            ));
                    break;
                case "12":
                    salir = true; break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
