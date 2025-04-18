package dao;

import modelo.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO implements DAO<Inscripcion> {
    private Connection connection;
    public InscripcionDAO(Connection connection) { this.connection = connection; }

    @Override
    public void crear(Inscripcion inscripcion) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_insert_inscripcion(?, ?, ?)}")) {
            cs.setInt(1, inscripcion.getIdSocio());
            cs.setInt(2, inscripcion.getIdExcursion());
            long tiempo = inscripcion.getFechaInscripcion().getTime();
            java.sql.Date fechaSQL = new java.sql.Date(tiempo);
            cs.setDate(3, fechaSQL);
            cs.executeUpdate();
        }
    }

    @Override
    public Inscripcion obtenerPorId(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM inscripcion WHERE id_inscripcion = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Inscripcion(rs.getInt("id_inscripcion"), rs.getInt("id_socio"), rs.getInt("id_excursion"), rs.getDate("fecha_inscripcion"));
            }
        }
        return null;
    }

    @Override
    public List<Inscripcion> obtenerTodos() throws SQLException {
        List<Inscripcion> lista = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM inscripcion")) {
            while (rs.next()) {
                lista.add(new Inscripcion(rs.getInt("id_inscripcion"), rs.getInt("id_socio"), rs.getInt("id_excursion"), rs.getDate("fecha_inscripcion")));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Inscripcion inscripcion) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_update_inscripcion(?, ?, ?, ?)}")) {
            cs.setInt(1, inscripcion.getIdInscripcion());
            cs.setInt(2, inscripcion.getIdSocio());
            cs.setInt(3, inscripcion.getIdExcursion());
            long tiempo = inscripcion.getFechaInscripcion().getTime();
            java.sql.Date fechaSQL = new java.sql.Date(tiempo);
            cs.setDate(4, fechaSQL);
            cs.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_delete_inscripcion(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }
}
