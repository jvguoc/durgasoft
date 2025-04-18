package controlador;

import dao.ExcursionDAO;
import dao.InscripcionDAO;
import dao.DAOFactory;
import modelo.Excursion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControladorExcursion {
    private ExcursionDAO excursionDAO;
    private InscripcionDAO inscripcionDAO;

    public ControladorExcursion() throws SQLException {
        Connection conn = dao.Conexion.getConnection();
        this.excursionDAO = DAOFactory.createExcursionDAO();
        this.inscripcionDAO = DAOFactory.createInscripcionDAO();
    }

    public void registrarExcursion(Excursion excursion) throws SQLException {
        excursionDAO.crear(excursion);
    }

    public List<Excursion> obtenerExcursiones() throws SQLException {
        return excursionDAO.obtenerTodos();
    }

    public Excursion buscarExcursionPorId(int id) throws SQLException {
        return excursionDAO.obtenerPorId(id);
    }

    public void actualizarExcursion(Excursion excursion) throws SQLException {
        excursionDAO.actualizar(excursion);
    }

    public void eliminarExcursion(int idExcursion) throws SQLException {
        // Primero borrar inscripciones de esta excursión para evitar FK RESTRICT
        inscripcionDAO.eliminarPorExcursion(idExcursion);
        // Luego borrar la excursión
        excursionDAO.eliminar(idExcursion);
    }
}