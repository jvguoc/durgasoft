package controlador;

import dao.InscripcionDAO;
import modelo.Inscripcion;
import modelo.Socio;
import modelo.Excursion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

            Socio socio = cs.buscarSocioPorId(idSocio);
            Excursion excursion = ce.buscarExcursionPorId(idExcursion);

            if (socio == null) {
                throw new Exception("Socio no encontrado.");
            }
            if (excursion == null) {
                throw new Exception("Excursión no encontrada.");
            }
            if (excursion.getPlazasDisponibles() <= 0) {
                throw new Exception("Sin plazas disponibles.");
            }

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

    public void eliminarInscripcion(int idInscripcion) throws SQLException {
        inscripcionDAO.eliminar(idInscripcion);
    }

    public List<Inscripcion> obtenerInscripciones() throws SQLException {
        return inscripcionDAO.obtenerTodos();
    }
}
