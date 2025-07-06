package xyz.cereshost.abd.storage;

import lombok.Getter;
import xyz.cereshost.abd.Service;

import java.sql.*;

@Getter
public abstract class DataBaseMySql {
    private static Connection connection;
    private boolean isActive;

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "actividad";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    protected DataBaseMySql() {
        try (Connection connection = getConnection()) {//revisa si la tabla existe
            DatabaseMetaData dbMetaData = connection.getMetaData();
            try (ResultSet resultSet = dbMetaData.getTables(null, null, "register", null)) {
                Service.sendMessage("DataBase %s Ok", Class.class.getSimpleName());
            }
        } catch (RuntimeException | SQLException e) {
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(getSqlTable());
                Service.sendMessage("DataBase %s Creada", Class.class.getSimpleName());
            } catch (SQLException ee) {
                throw new RuntimeException(ee);
            }
        }
    }

    private static void connect() {
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
        try {
            connection = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (SQLException e) {
            Service.sendMessage("Error al conectar con la base de datos", e);
        }
    }

    protected static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) connect();
        return connection;
    }

    protected abstract String getSqlTable();

}
