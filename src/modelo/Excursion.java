package modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "excursion")
public class Excursion {
    @Override
    public String toString() {
        return "ID: " + idExcursion + ", Nombre: " + nombre +
                ", Lugar: " + lugar + ", Fecha: " + fecha +
                ", Plazas disponibles: " + plazasDisponibles;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_excursion")
    private Integer idExcursion;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String lugar;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    @Column(name = "plazas_disponibles", nullable = false)
    private int plazasDisponibles;

    @OneToMany(mappedBy = "excursion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Excursion() {}

    public Excursion(String nombre, Date fecha, String lugar, int plazasDisponibles) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.plazasDisponibles = plazasDisponibles;
    }

    public Integer getIdExcursion() { return idExcursion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public int getPlazasDisponibles() { return plazasDisponibles; }
    public void setPlazasDisponibles(int plazasDisponibles) { this.plazasDisponibles = plazasDisponibles; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}