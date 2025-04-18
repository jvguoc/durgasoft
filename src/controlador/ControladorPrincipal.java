package controlador;

import dao.Conexion;
import java.sql.Connection;

public class ControladorPrincipal {
    private ControladorSocio cs;
    private ControladorExcursion ce;
    private ControladorInscripcion ci;

    public ControladorPrincipal() throws Exception {
        Connection conn = Conexion.getConnection();
        this.cs = new ControladorSocio();
        this.ce = new ControladorExcursion();
        this.ci = new ControladorInscripcion(cs, ce, conn);
    }

    public ControladorSocio getControladorSocio() { return cs; }
    public ControladorExcursion getControladorExcursion() { return ce; }
    public ControladorInscripcion getControladorInscripcion() { return ci; }
}