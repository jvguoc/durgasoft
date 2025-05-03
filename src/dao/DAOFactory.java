package dao;

import modelo.Socio;
import modelo.Excursion;
import modelo.Inscripcion;

public class DAOFactory {
    public static DAO<Socio> getSocioDAO() {
        return new SocioDAOJPA();
    }
    public static DAO<Excursion> getExcursionDAO() {
        return new ExcursionDAOJPA();
    }
    public static DAO<Inscripcion> getInscripcionDAO() {
        return new InscripcionDAOJPA();
    }
}
