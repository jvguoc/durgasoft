package dao;

import modelo.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO implements DAO<Inscripcion> {
    private Connection connection;

    public InscripcionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Inscripcion inscripcion) throws SQLException {
        String sql = "INSERT INTO inscripcion (id_socio, id_excursion, fecha_inscripcion) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, inscripcion.getIdSocio());
            ps.setInt(2, inscripcion.getIdExcursion());
            ps.setDate(3, new java.sql.Date(inscripcion.getFechaInscripcion().getTime()));
            ps.executeUpdate();
        }
    }

    @Override
    public Inscripcion obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM inscripcion WHERE id_inscripcion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Inscripcion(
                            rs.getInt("id_inscripcion"),
                            rs.getInt("id_socio"),
                            rs.getInt("id_excursion"),
                            rs.getDate("fecha_inscripcion")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Inscripcion> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM inscripcion";
        List<Inscripcion> lista = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Inscripcion(
                        rs.getInt("id_inscripcion"),
                        rs.getInt("id_socio"),
                        rs.getInt("id_excursion"),
                        rs.getDate("fecha_inscripcion")
                ));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Inscripcion inscripcion) throws SQLException {
        String sql = "UPDATE inscripcion SET id_socio = ?, id_excursion = ?, fecha_inscripcion = ? WHERE id_inscripcion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, inscripcion.getIdSocio());
            ps.setInt(2, inscripcion.getIdExcursion());
            ps.setDate(3, new java.sql.Date(inscripcion.getFechaInscripcion().getTime()));
            ps.setInt(4, inscripcion.getIdInscripcion());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM inscripcion WHERE id_inscripcion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Elimina todas las inscripciones asociadas a una excursión.
     */
    public void eliminarPorExcursion(int idExcursion) throws SQLException {
        String sql = "DELETE FROM inscripcion WHERE id_excursion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idExcursion);
            ps.executeUpdate();
        }
    }
}