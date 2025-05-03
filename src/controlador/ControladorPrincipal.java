package controlador;

public class ControladorPrincipal {
    private ControladorSocio cs = new ControladorSocio();
    private ControladorExcursion ce = new ControladorExcursion();
    private ControladorInscripcion ci = new ControladorInscripcion();
    public ControladorSocio getControladorSocio() { return cs; }
    public ControladorExcursion getControladorExcursion() { return ce; }
    public ControladorInscripcion getControladorInscripcion() { return ci; }
}