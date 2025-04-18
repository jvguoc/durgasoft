package vista;

import controlador.ControladorPrincipal;
import java.util.Date;
import java.util.Scanner;
import modelo.Socio;
import modelo.Excursion;
import controlador.*;

public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {
        scanner = new Scanner(System.in);
    }

    public String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void iniciarMenu(ControladorPrincipal controlador) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrar Socio");
            System.out.println("2. Registrar Excursión");
            System.out.println("3. Inscribir Socio en Excursión");
            System.out.println("4. Consultar Socios");
            System.out.println("5. Salir");
            String opcion = leerEntrada("Seleccione una opción: ");

            switch (opcion) {
                case "1":
                    // Registrar socio
                    String nombreSocio = leerEntrada("Ingrese el nombre del socio: ");
                    String localidadSocio = leerEntrada("Ingrese la localidad del socio: ");
                    String telefonoSocio = leerEntrada("Ingrese el teléfono del socio: ");
                    controlador.getControladorSocio().registrarSocio(new Socio(nombreSocio, localidadSocio, telefonoSocio));
                    mostrarMensaje("Socio registrado exitosamente.");
                    break;
                case "2":
                    // Registrar excursión
                    String nombreExcursion = leerEntrada("Ingrese el nombre de la excursión: ");
                    String lugarExcursion = leerEntrada("Ingrese el lugar de la excursión: ");
                    Date fechaExcursion = new Date();  // O usar un método de entrada más detallado para la fecha
                    int idExcursion = Integer.parseInt(leerEntrada("Ingrese el ID de la excursión: "));
                    int plazasDisponibles = Integer.parseInt(leerEntrada("Ingrese las plazas disponibles: "));

                    // Ahora creamos la excursión con todos los parámetros
                    controlador.getControladorExcursion().registrarExcursion(new Excursion(idExcursion, nombreExcursion, fechaExcursion, lugarExcursion, plazasDisponibles));
                    mostrarMensaje("Excursión registrada exitosamente.");
                    break;
                case "3":
                    // Inscribir socio en excursión
                    try {
                        int idSocio = Integer.parseInt(leerEntrada("Ingrese el ID del socio: "));
                        int idExcursionInscripcion = Integer.parseInt(leerEntrada("Ingrese el ID de la excursión: "));
                        controlador.getControladorInscripcion().inscribirSocio(idSocio, idExcursionInscripcion);
                        mostrarMensaje("Socio inscrito en la excursión.");
                    } catch (SocioNoEncontradoException e) {
                        mostrarMensaje("Error: " + e.getMessage());
                    } catch (ExcursionNoEncontradaException e) {
                        mostrarMensaje("Error: " + e.getMessage());
                    } catch (PlazasInsuficientesException e) {
                        mostrarMensaje("Error: " + e.getMessage());
                    } catch (InscripcionDuplicadaException e) {
                        mostrarMensaje("Error: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        mostrarMensaje("Por favor, ingrese un número válido.");
                    }
                    break;
                case "4":
                    // Mostrar lista de socios
                    controlador.getControladorSocio().obtenerSocios().forEach(socio -> mostrarMensaje(socio.toString()));
                    break;
                case "5":
                    continuar = false;
                    mostrarMensaje("Saliendo...");
                    break;
                default:
                    mostrarMensaje("Opción no válida.");
            }
        }
    }

}
