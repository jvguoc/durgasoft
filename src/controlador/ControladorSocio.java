package controlador;

import dao.DAO;
import dao.DAOFactory;
import modelo.Socio;
import java.util.List;

public class ControladorSocio {
    private DAO<Socio> dao = DAOFactory.getSocioDAO();
    public void registrarSocio(Socio s) { dao.create(s); }
    public List<Socio> obtenerSocios() { return dao.findAll(); }
    public Socio buscarSocioPorId(int id) { return dao.findById(id); }
    public void modificarSocio(Socio s) { dao.update(s); }
    public void eliminarSocio(int id) { dao.delete(id); }
}
