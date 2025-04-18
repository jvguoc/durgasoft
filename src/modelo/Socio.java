package modelo;

public class Socio {
    private int idSocio;
    private String nombre;
    private String localidad;
    private String telefono;

    public Socio(String nombre, String localidad, String telefono) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public Socio(int idSocio, String nombre, String localidad, String telefono) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public int getIdSocio() { return idSocio; }
    public String getNombre() { return nombre; }
    public String getLocalidad() { return localidad; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "ID: " + idSocio + ", Nombre: " + nombre + ", Localidad: " + localidad + ", Teléfono: " + telefono;
    }
}