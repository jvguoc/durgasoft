package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Cargar el driver JDBC de MySQL
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: MySQL JDBC Driver no encontrado.", e);
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/fp058";
    private static final String USER = "excursiones";
    private static final String PASSWORD = "password";
    private static Connection connection;

    private Conexion() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
