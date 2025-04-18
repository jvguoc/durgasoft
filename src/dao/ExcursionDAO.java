package dao;

import modelo.Excursion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDAO implements DAO<Excursion> {
    private Connection connection;
    public ExcursionDAO(Connection connection) { this.connection = connection; }

    @Override
    public void crear(Excursion excursion) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_insert_excursion(?, ?, ?, ?)}")) {
            cs.setString(1, excursion.getNombre());
            cs.setString(2, excursion.getLugar());
            cs.setDate(3, new java.sql.Date(excursion.getFecha().getTime()));
            cs.setInt(4, excursion.getPlazasDisponibles());
            cs.executeUpdate();
        }
    }

    @Override
    public Excursion obtenerPorId(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM excursion WHERE id_excursion = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Excursion(rs.getInt("id_excursion"), rs.getString("nombre"), rs.getDate("fecha"), rs.getString("lugar"), rs.getInt("plazas_disponibles"));
            }
        }
        return null;
    }

    @Override
    public List<Excursion> obtenerTodos() throws SQLException {
        List<Excursion> lista = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM excursion")) {
            while (rs.next()) {
                lista.add(new Excursion(rs.getInt("id_excursion"), rs.getString("nombre"), rs.getDate("fecha"), rs.getString("lugar"), rs.getInt("plazas_disponibles")));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Excursion excursion) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_update_excursion(?, ?, ?, ?, ?)}")) {
            cs.setInt(1, excursion.getIdExcursion());
            cs.setString(2, excursion.getNombre());
            cs.setDate(3, new java.sql.Date(excursion.getFecha().getTime()));
            cs.setString(4, excursion.getLugar());
            cs.setInt(5, excursion.getPlazasDisponibles());
            cs.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_delete_excursion(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }
}