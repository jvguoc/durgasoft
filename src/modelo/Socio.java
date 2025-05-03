package modelo;

import javax.persistence.*;

@Entity
@Table(name = "socio")
public class Socio {
    @Override
    public String toString() {
        return "ID: " + idSocio + ", Nombre: " + nombre +
                ", Localidad: " + localidad + ", Teléfono: " + telefono;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private Integer idSocio;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    private String telefono;

    public Socio() {}

    public Socio(String nombre, String localidad, String telefono) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public Integer getIdSocio() { return idSocio; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}