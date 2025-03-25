package modelo;

import java.text.SimpleDateFormat; // Temporal hasta encontrar la mejor forma de handlear la fecha
import java.util.Date;

public class Excursion {
    private final int idExcursion; // TODO: Revisar si por lógica debería ser final o interesa cambiar la id en algún punto
    private static int contadorId = 1;
    private String nombre;
    private Date fecha;
    private String lugar;
    private int plazasDisponibles;

    public Excursion(int idExcursion, String nombre, Date fecha, String lugar, int plazasDisponibles) {
        this.idExcursion = contadorId++;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.plazasDisponibles = plazasDisponibles;
    }

    public void crear() {
        System.out.println("Creando excursión: " + this.nombre);
    }

    public void modificar(String nuevoNombre, Date nuevaFecha, String nuevoLugar, int nuevasPlazas) {
        this.nombre = nuevoNombre;
        this.fecha = nuevaFecha;
        this.lugar = nuevoLugar;
        this.plazasDisponibles = nuevasPlazas;
        System.out.println("Datos de la excursión actualizados.");
    }

    public void eliminar() {
        System.out.println("Eliminando excursión: " + this.nombre);
    }

    @Override
    public String toString() {
        return "ID: " + idExcursion +
                ", Nombre: " + nombre +
                ", Fecha: " + getFechaFormateada() +
                ", Lugar: " + lugar +
                ", Plazas disponibles: " + plazasDisponibles;
    }

    public int getIdExcursion() {
        return idExcursion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }
}

