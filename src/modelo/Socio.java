package modelo;

public class Socio {
    private int idSocio;
    private static int contadorId = 1;
    private String nombre;
    private String localidad;
    private String telefono;

    public Socio(int idSocio, String nombre, String localidad, String telefono) {
        this.idSocio = contadorId++;
        this.nombre = nombre;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public void registrar() {
        System.out.println("Registrando socio: " + this.nombre);
    }

    public void modificar(String nuevoNombre, String nuevaLocalidad, String nuevoTelefono) {
        this.nombre = nuevoNombre;
        this.localidad = nuevaLocalidad;
        this.telefono = nuevoTelefono;
        System.out.println("Datos del socio actualizados.");
    }

    public void eliminar() {
        System.out.println("Eliminando socio: " + this.nombre);
    }

    @Override
    public String toString() {
        return "ID: " + idSocio + ", Nombre: " + nombre + ", Localidad: " + localidad + ", Teléfono: " + telefono;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public String getNombre() {
        return nombre;
    }
}
