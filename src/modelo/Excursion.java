package modelo;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

public class Excursion {
    private int idExcursion;
    private String nombre;
    private Date fecha;
    private String lugar;
    private int plazasDisponibles;
    private List<Integer> sociosInscritos = new ArrayList<>();

    public Excursion(String nombre, Date fecha, String lugar, int plazasDisponibles) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.plazasDisponibles = plazasDisponibles;
    }

    public Excursion(int idExcursion, String nombre, Date fecha, String lugar, int plazasDisponibles) {
        this.idExcursion = idExcursion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.plazasDisponibles = plazasDisponibles;
    }

    public int getIdExcursion() { return idExcursion; }
    public String getNombre() { return nombre; }
    public Date getFecha() { return fecha; }
    public String getLugar() { return lugar; }
    public int getPlazasDisponibles() { return plazasDisponibles; }
    public List<Integer> getSociosInscritos() { return sociosInscritos; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public void setPlazasDisponibles(int plazasDisponibles) { this.plazasDisponibles = plazasDisponibles; }

    public String getFechaFormateada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    @Override
    public String toString() {
        return "ID: " + idExcursion + ", Nombre: " + nombre + ", Fecha: " + getFechaFormateada() + ", Lugar: " + lugar + ", Plazas disponibles: " + plazasDisponibles;
    }
}