package modelo;

import java.text.SimpleDateFormat; // Temporal hasta encontrar la mejor forma de handlear la fecha
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Excursion {
    private int idExcursion;
    private static int contadorId = 1;
    private String nombre;
    private Date fecha;
    private String lugar;
    private int plazasDisponibles;
    private List<Integer> sociosInscritos = new ArrayList<>(); // Lista de socios inscritos

    public Excursion(int idExcursion, String nombre, Date fecha, String lugar, int plazasDisponibles) {
        this.idExcursion = contadorId++;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.plazasDisponibles = plazasDisponibles;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormateada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    // Nuevo método para obtener la lista de socios inscritos
    public List<Integer> getSociosInscritos() {
        return sociosInscritos;
    }
}
