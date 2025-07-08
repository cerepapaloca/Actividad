package xyz.cereshost.abd.storage;

import lombok.Getter;
import xyz.cereshost.abd.Service;

import java.sql.*;
import java.util.List;

@Getter
public abstract class DataBaseMySql<T> {
    private static Connection connection;

    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE = "actividad";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @SuppressWarnings("SqlSourceToSinkFlow")
    public DataBaseMySql() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(getSqlTable());
            Service.sendMessage("Tabla %s ok", this.getClass().getSimpleName());
        } catch (SQLException e) {
            Service.sendMessage("Error al crear la tabla", e);
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

    public abstract T getData(String value);

    public abstract List<T> getAll();

    public abstract void addRow(T data);


}
