package dao;

import modelo.Socio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO implements DAO<Socio> {
    private Connection connection;
    public SocioDAO(Connection connection) { this.connection = connection; }

    //  https://www.baeldung.com/java-dao-pattern
    @Override
    public void crear(Socio socio) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_insert_socio(?, ?, ?)}")) {
            cs.setString(1, socio.getNombre());
            cs.setString(2, socio.getLocalidad());
            cs.setString(3, socio.getTelefono());
            cs.executeUpdate();
        }
    }

    @Override
    public Socio obtenerPorId(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM socio WHERE id_socio = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Socio(rs.getInt("id_socio"), rs.getString("nombre"), rs.getString("localidad"), rs.getString("telefono"));
            }
        }
        return null;
    }

    @Override
    public List<Socio> obtenerTodos() throws SQLException {
        List<Socio> lista = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM socio")) {
            while (rs.next()) {
                lista.add(new Socio(rs.getInt("id_socio"), rs.getString("nombre"), rs.getString("localidad"), rs.getString("telefono")));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Socio socio) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_update_socio(?, ?, ?, ?)}")) {
            cs.setInt(1, socio.getIdSocio());
            cs.setString(2, socio.getNombre());
            cs.setString(3, socio.getLocalidad());
            cs.setString(4, socio.getTelefono());
            cs.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (CallableStatement cs = connection.prepareCall("{CALL sp_delete_socio(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
        }
    }
}