package controlador;

public class ControladorPrincipal {
    private ControladorSocio controladorSocio;
    private ControladorExcursion controladorExcursion;
    private ControladorInscripcion controladorInscripcion;

    public ControladorPrincipal() {
        this.controladorSocio = new ControladorSocio();
        this.controladorExcursion = new ControladorExcursion();
        this.controladorInscripcion = new ControladorInscripcion(controladorSocio, controladorExcursion);
    }

    public ControladorSocio getControladorSocio() {
        return controladorSocio;
    }

    public ControladorExcursion getControladorExcursion() {
        return controladorExcursion;
    }

    public ControladorInscripcion getControladorInscripcion() {
        return controladorInscripcion;
    }
}
