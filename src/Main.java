import controlador.Controlador;
import modelo.*;
import vista.VistaConsola;

import controlador.SocioNoEncontradoException;
import controlador.ExcursionNoEncontradaException;
import controlador.PlazasInsuficientesException;
import controlador.InscripcionDuplicadaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        VistaConsola vista = new VistaConsola();

        boolean continuar = true;

        while (continuar) {
            // Mostrar opciones
            vista.mostrarMensaje("\n--- Durgasoft® Gestor de Excursiones ---");
            vista.mostrarMensaje("1. Registrar Socio");
            vista.mostrarMensaje("2. Registrar Excursión");
            vista.mostrarMensaje("3. Ver todos los Socios");
            vista.mostrarMensaje("4. Ver todas las Excursiones");
            vista.mostrarMensaje("5. Modificar Socio");
            vista.mostrarMensaje("6. Eliminar Socio del sistema");
            vista.mostrarMensaje("7. Inscribir Socio en Excursión");
            vista.mostrarMensaje("8. Eliminar Socio de Excursión");
            vista.mostrarMensaje("9. Salir\n");

            String opcion = vista.leerEntrada("Selecciona una opción: ");

            switch (opcion) {
                case "1":
                    // 1. Registrar Socio
                    String nombreSocio = vista.leerEntrada("Introduce el nombre del socio: ");
                    String localidad = vista.leerEntrada("Introduce la localidad del socio: ");
                    String telefono = vista.leerEntrada("Introduce el teléfono del socio: ");
                    Socio socio = new Socio(0, nombreSocio, localidad, telefono);
                    controlador.registrarSocio(socio);
                    break;

                case "2":
                    // 2. Registrar Excursión
                    String nombreExcursion = vista.leerEntrada("Introduce el nombre de la excursión: ");
                    String lugar = vista.leerEntrada("Introduce el lugar de la excursión: ");
                    int plazas = Integer.parseInt(vista.leerEntrada("Introduce el número de plazas disponibles: "));

                    // Test validación fecha
                    Date fecha = null;
                    boolean fechaValida = false;
                    while (!fechaValida) {
                        String fechaStr = vista.leerEntrada("Introduce la fecha de la excursión (dd/MM/yyyy): ");
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        formato.setLenient(false);  // Test https://docs.oracle.com/javase/8/docs/api/java/text/DateFormat.html#setLenient-boolean-

                        try {
                            fecha = formato.parse(fechaStr);
                            fechaValida = true;
                        } catch (ParseException e) {
                            vista.mostrarMensaje("\n !! Formato de fecha incorrecto. Asegúrate de utilizar dd/MM/yyyy !! \n");
                        }
                    }

                    Excursion excursion = new Excursion(0, nombreExcursion, fecha, lugar, plazas);
                    controlador.registrarExcursion(excursion);
                    break;

                case "3":
                    // 3. Ver todos los Socios
                    controlador.mostrarSocios();
                    break;

                case "4":
                    // 4. Ver todas las Excursiones
                    controlador.mostrarExcursiones();
                    break;

                case "5":
                    // 5. Modificar Socio
                    // TODO: Implementar un sub-menú con opción a elegir qué editar
                    int idSocioModificar = Integer.parseInt(vista.leerEntrada("Introduce el ID del socio a modificar: "));
                    String nuevoNombre = vista.leerEntrada("Introduce el nuevo nombre del socio: ");
                    String nuevaLocalidad = vista.leerEntrada("Introduce la nueva localidad del socio: ");
                    String nuevoTelefono = vista.leerEntrada("Introduce el nuevo teléfono del socio: ");

                    try {
                        controlador.modificarSocio(idSocioModificar, nuevoNombre, nuevaLocalidad, nuevoTelefono);
                    } catch (SocioNoEncontradoException e) {
                        vista.mostrarMensaje("Error: " + e.getMessage());
                    }
                    break;

                case "6":
                    // 6. Eliminar Socio
                    int idSocioEliminar = Integer.parseInt(vista.leerEntrada("Introduce el ID del socio a eliminar: "));
                    try {
                        controlador.eliminarSocio(idSocioEliminar);  // Puede lanzar SocioNoEncontradoException
                    } catch (SocioNoEncontradoException e) {
                        vista.mostrarMensaje(e.getMessage());  // Muestra el mensaje de la excepción
                    }
                    break;


                case "7":
                    // 7. Inscribir Socio en Excursión
                    int idSocioInscribir = Integer.parseInt(vista.leerEntrada("Introduce el ID del socio: "));
                    int idExcursionInscribir = Integer.parseInt(vista.leerEntrada("Introduce el ID de la excursión: "));
                    try {
                        controlador.inscribirSocio(idSocioInscribir, idExcursionInscribir);
                    } catch (SocioNoEncontradoException | ExcursionNoEncontradaException | PlazasInsuficientesException | InscripcionDuplicadaException e) {
                        vista.mostrarMensaje("Error: " + e.getMessage());
                    }
                    break;

                case "8":
                    // 8. Eliminar Socio de Excursión
                    int idSocioEchar = Integer.parseInt(vista.leerEntrada("Introduce el ID del socio a eliminar de la excursión: "));
                    int idExcursionEliminar = Integer.parseInt(vista.leerEntrada("Introduce el ID de la excursión: "));
                    controlador.eliminarInscripcion(idSocioEchar, idExcursionEliminar);
                    break;

                case "9":
                    // 9. Salir
                    continuar = false;
                    vista.mostrarMensaje("Cerrando aplicación...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida. Intenta de nuevo.");
                    break;
            }
        }
    }
}
