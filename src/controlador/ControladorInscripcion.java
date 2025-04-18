package controlador;

import dao.InscripcionDAO;
import modelo.Inscripcion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ControladorInscripcion {
    private InscripcionDAO inscripcionDAO;
    private ControladorSocio cs;
    private ControladorExcursion ce;
    private Connection conn;

    public ControladorInscripcion(ControladorSocio cs, ControladorExcursion ce, Connection conn) throws SQLException {
        this.cs = cs;
        this.ce = ce;
        this.conn = conn;
        this.inscripcionDAO = new InscripcionDAO(conn);
    }

    public void inscribir(int idSocio, int idExcursion) throws Exception {
        try {
            conn.setAutoCommit(false);
            modelo.Socio socio = cs.buscarSocioPorId(idSocio);
            modelo.Excursion excursion = ce.buscarExcursionPorId(idExcursion);
            if (excursion.getPlazasDisponibles() <= 0) throw new Exception("Sin plazas disponibles");
            excursion.setPlazasDisponibles(excursion.getPlazasDisponibles() - 1);
            ce.actualizarExcursion(excursion);
            inscripcionDAO.crear(new Inscripcion(idSocio, idExcursion, new Date()));
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}