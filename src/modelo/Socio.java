package modelo;

public class Socio {
    private int idSocio;
    private String nombre;
    private String localidad;
    private String telefono;

    public Socio(int idSocio, String nombre, String localidad, String telefono) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public void registrar() {
        // TODO
    }

    public void modificar() {
        // TODO
    }

    public void eliminar(int i) {
        // TODO
    }

    public void consultar() {
        //TODO
    }

    // G & S
    public int getIdSocio() {
        return idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getTelefono() {
        return telefono;
    }
}
