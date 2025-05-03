package controlador;

import dao.DAO;
import dao.DAOFactory;
import modelo.Excursion;
import java.util.List;

public class ControladorExcursion {
    private DAO<Excursion> dao = DAOFactory.getExcursionDAO();
    public void registrarExcursion(Excursion e) { dao.create(e); }
    public List<Excursion> obtenerExcursiones() { return dao.findAll(); }
    public Excursion buscarExcursionPorId(int id) { return dao.findById(id); }
    public void actualizarExcursion(Excursion e) { dao.update(e); }
    public void eliminarExcursion(int id) { dao.delete(id); }
}