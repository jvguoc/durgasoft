package controlador;

import dao.DAO;
import dao.DAOFactory;
import modelo.Inscripcion;
import modelo.Socio;
import modelo.Excursion;
import java.util.Date;
import java.util.List;

public class ControladorInscripcion {
    private DAO<Inscripcion> ids = DAOFactory.getInscripcionDAO();
    private DAO<Socio> ss = DAOFactory.getSocioDAO();
    private DAO<Excursion> ee = DAOFactory.getExcursionDAO();
    public void inscribir(int idSocio, int idExcursion) {
        Socio s = ss.findById(idSocio);
        Excursion e = ee.findById(idExcursion);
        ids.create(new Inscripcion(s, e, new Date()));
    }
    public List<Inscripcion> obtenerInscripciones() { return ids.findAll(); }
    public void eliminarInscripcion(int id) { ids.delete(id); }
}