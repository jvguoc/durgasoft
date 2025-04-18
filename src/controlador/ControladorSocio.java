package controlador;

import dao.SocioDAO;
import modelo.Socio;
import java.sql.SQLException;
import java.util.List;

public class ControladorSocio {
    private SocioDAO socioDAO;

    public ControladorSocio() throws SQLException {
        this.socioDAO = dao.DAOFactory.createSocioDAO();
    }

    public void registrarSocio(Socio socio) throws SQLException {
        socioDAO.crear(socio);
    }

    public List<Socio> obtenerSocios() throws SQLException {
        return socioDAO.obtenerTodos();
    }

    public Socio buscarSocioPorId(int id) throws SQLException {
        return socioDAO.obtenerPorId(id);
    }

    public void modificarSocio(Socio socio) throws SQLException {
        socioDAO.actualizar(socio);
    }

    public void eliminarSocio(int id) throws SQLException {
        socioDAO.eliminar(id);
    }
}