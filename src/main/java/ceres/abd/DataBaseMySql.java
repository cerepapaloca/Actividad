package ceres.abd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataBaseMySql {
    private static Connection connection;

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "abd";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    protected DataBaseMySql() {
        createTable();
    }

    private static void connect() {
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        try {
            connection = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (SQLException e) {
            Service.sendLog("Error al conectar con la base de datos", e);
        }
    }

    protected static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) connect();
        return connection;
    }

    protected abstract void createTable();

}
