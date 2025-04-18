package dao;

import modelo.Excursion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDAO implements DAO<Excursion> {
    private Connection connection;

    public ExcursionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Excursion excursion) throws SQLException {
        String sql = "INSERT INTO excursion (nombre, lugar, fecha, plazas_disponibles) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, excursion.getNombre());
            ps.setString(2, excursion.getLugar());
            ps.setDate(3, new java.sql.Date(excursion.getFecha().getTime()));
            ps.setInt(4, excursion.getPlazasDisponibles());
            ps.executeUpdate();
        }
    }

    @Override
    public Excursion obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM excursion WHERE id_excursion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Excursion(
                            rs.getInt("id_excursion"),
                            rs.getString("nombre"),
                            rs.getDate("fecha"),
                            rs.getString("lugar"),
                            rs.getInt("plazas_disponibles")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Excursion> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM excursion";
        List<Excursion> lista = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Excursion(
                        rs.getInt("id_excursion"),
                        rs.getString("nombre"),
                        rs.getDate("fecha"),
                        rs.getString("lugar"),
                        rs.getInt("plazas_disponibles")
                ));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Excursion excursion) throws SQLException {
        String sql = "UPDATE excursion SET nombre = ?, lugar = ?, fecha = ?, plazas_disponibles = ? WHERE id_excursion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, excursion.getNombre());
            ps.setString(2, excursion.getLugar());
            ps.setDate(3, new java.sql.Date(excursion.getFecha().getTime()));
            ps.setInt(4, excursion.getPlazasDisponibles());
            ps.setInt(5, excursion.getIdExcursion());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM excursion WHERE id_excursion = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}