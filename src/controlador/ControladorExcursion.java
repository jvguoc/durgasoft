package controlador;

import dao.ExcursionDAO;
import modelo.Excursion;
import java.sql.SQLException;
import java.util.List;

public class ControladorExcursion {
    private ExcursionDAO excursionDAO;

    public ControladorExcursion() throws SQLException {
        this.excursionDAO = dao.DAOFactory.createExcursionDAO();
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

    public void eliminarExcursion(int id) throws SQLException {
        excursionDAO.eliminar(id);
    }
}