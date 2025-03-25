package modelo;

import java.util.Date;

public class Inscripcion {
    private Socio socio;
    private Excursion excursion;
    private Date fechaInscripcion;

    public Inscripcion(Socio socio, Excursion excursion, Date fechaInscripcion) {
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public void inscribir() {
        // Lógica para realizar la inscripción
        System.out.println("Socio " + socio.getNombre() + " se ha inscrito en la excursión: " + excursion.getNombre());
        // Actualizar plazas disponibles de la excursión
        excursion.setPlazasDisponibles(excursion.getPlazasDisponibles() - 1);
    }

    public void cancelar() {
        // Lógica para cancelar la inscripción
        System.out.println("Inscripción de " + socio.getNombre() + " cancelada en la excursión: " + excursion.getNombre());
        // Actualizar plazas disponibles de la excursión
        excursion.setPlazasDisponibles(excursion.getPlazasDisponibles() + 1);
    }

    public void consultar() {
        // Consultar información sobre la inscripción
        System.out.println("Inscripción de " + socio.getNombre() + " en la excursión: " + excursion.getNombre() + " realizada el: " + fechaInscripcion);
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Socio getSocio() {
        return socio;
    }

    public Excursion getExcursion() {
        return excursion;
    }

}
