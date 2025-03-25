package modelo;

public class Inscripcion {
    private Date fechaInscripcion;

    public Inscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public void inscripcion() {
        System.out.println("Inscripción realizada en fecha " + fechaInscripcion);
    }

    public void cancelar() {
        System.out.println("Inscripción cancelada.");
    }
}
