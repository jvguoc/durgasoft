package modelo;

import java.util.Date;

public class Inscripcion {
    private int idInscripcion;
    private int idSocio;
    private int idExcursion;
    private Date fechaInscripcion;

    public Inscripcion(int idSocio, int idExcursion, Date fechaInscripcion) {
        this.idSocio = idSocio;
        this.idExcursion = idExcursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Inscripcion(int idInscripcion, int idSocio, int idExcursion, Date fechaInscripcion) {
        this.idInscripcion = idInscripcion;
        this.idSocio = idSocio;
        this.idExcursion = idExcursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getIdInscripcion() { return idInscripcion; }
    public int getIdSocio() { return idSocio; }
    public int getIdExcursion() { return idExcursion; }
    public Date getFechaInscripcion() { return fechaInscripcion; }

    public void setFechaInscripcion(Date fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
}