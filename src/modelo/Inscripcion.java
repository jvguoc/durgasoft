package modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {
    @Override
    public String toString() {
        return "ID Inscripción: " + idInscripcion +
                ", Socio: " + socio.getIdSocio() +
                ", Excursión: " + excursion.getIdExcursion() +
                ", Fecha: " + fechaInscripcion;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Integer idInscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_excursion", nullable = false)
    private Excursion excursion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inscripcion", nullable = false)
    private Date fechaInscripcion;

    public Inscripcion() {}

    public Inscripcion(Socio socio, Excursion excursion, Date fechaInscripcion) {
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getIdInscripcion() { return idInscripcion; }
    public Socio getSocio() { return socio; }
    public Excursion getExcursion() { return excursion; }
    public Date getFechaInscripcion() { return fechaInscripcion; }
}