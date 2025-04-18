package dao;

import java.sql.SQLException;

public class DAOFactory {
    public static SocioDAO createSocioDAO() throws SQLException {
        return new SocioDAO(Conexion.getConnection());
    }
    public static ExcursionDAO createExcursionDAO() throws SQLException {
        return new ExcursionDAO(Conexion.getConnection());
    }
    public static InscripcionDAO createInscripcionDAO() throws SQLException {
        return new InscripcionDAO(Conexion.getConnection());
    }
}