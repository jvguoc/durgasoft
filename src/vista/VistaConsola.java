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
            System.out.println(" --- Durgasoft© Gestor de Excursiones ---");
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
            System.out.print("Seleccione una opción: ");
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
                    System.out.print("ID Socio a modificar: "); int idSocMod = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo nombre: "); String nomMod = scanner.nextLine();
                    System.out.print("Nueva localidad: "); String locMod = scanner.nextLine();
                    System.out.print("Nuevo teléfono: "); String telMod = scanner.nextLine();
                    ctrl.getControladorSocio().modificarSocio(new Socio(idSocMod, nomMod, locMod, telMod));
                    System.out.println("Socio modificado.");
                    break;
                case "3":
                    System.out.print("ID Socio a eliminar: "); int idSocDel = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorSocio().eliminarSocio(idSocDel);
                    System.out.println("Socio eliminado.");
                    break;
                case "4":
                    ctrl.getControladorSocio().obtenerSocios().forEach(System.out::println);
                    break;
                case "5":
                    System.out.print("Nombre excursión: "); String ne = scanner.nextLine();
                    System.out.print("Lugar: "); String lu = scanner.nextLine();
                    System.out.print("Fecha (dd/MM/yyyy): "); String fechaStr = scanner.nextLine();
                    Date fe;
                    try {
                        fe = new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida, se usará la fecha actual.");
                        fe = new Date();
                    }
                    System.out.print("Plazas: "); int pl = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorExcursion().registrarExcursion(new Excursion(ne, fe, lu, pl));
                    System.out.println("Excursión registrada.");
                    break;
                case "6":
                    System.out.print("ID Excursión a modificar: "); int idExcMod = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo nombre: "); String neMod = scanner.nextLine();
                    System.out.print("Nueva fecha (dd/MM/yyyy): "); String fechaModStr = scanner.nextLine();
                    Date fechaMod;
                    try {
                        fechaMod = new SimpleDateFormat("dd/MM/yyyy").parse(fechaModStr);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida, se usará la fecha actual.");
                        fechaMod = new Date();
                    }
                    System.out.print("Nuevo lugar: "); String luMod = scanner.nextLine();
                    System.out.print("Nuevas plazas disponibles: "); int plMod = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorExcursion().actualizarExcursion(new Excursion(idExcMod, neMod, fechaMod, luMod, plMod));
                    System.out.println("Excursión modificada.");
                    break;
                case "7":
                    System.out.print("ID Excursión a eliminar: "); int idExcDel = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorExcursion().eliminarExcursion(idExcDel);
                    System.out.println("Excursión eliminada.");
                    break;
                case "8":
                    ctrl.getControladorExcursion().obtenerExcursiones().forEach(System.out::println);
                    break;
                case "9":
                    System.out.print("ID Socio: "); int idSoc = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID Excursión: "); int idExc = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorInscripcion().inscribir(idSoc, idExc);
                    System.out.println("Inscripción completada.");
                    break;
                case "10":
                    System.out.print("ID Inscripción a eliminar: "); int idInsDel = Integer.parseInt(scanner.nextLine());
                    ctrl.getControladorInscripcion().eliminarInscripcion(idInsDel);
                    System.out.println("Inscripción eliminada.");
                    break;
                case "11":
                    List<Inscripcion> insList = ctrl.getControladorInscripcion().obtenerInscripciones();
                    insList.forEach(ins -> System.out.println(
                            "ID Inscripción: " + ins.getIdInscripcion() +
                                    ", Socio: " + ins.getIdSocio() +
                                    ", Excursión: " + ins.getIdExcursion() +
                                    ", Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(ins.getFechaInscripcion())
                    ));
                    break;
                case "12":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
