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
