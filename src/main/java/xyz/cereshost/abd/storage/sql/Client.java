package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `Cliente` (
                  `dni` varchar(255) PRIMARY KEY,
                  `nombre` varchar(64) NOT NULL,
                  `direccion` varchar(30),
                  `telefono` varchar(30)
                );
                """;
    }


    public static void addRow(String dni, String name, String address, String phone) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO Cliente(dni, nombre, direccion, telefono) " +
                    "VALUES (?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setString(4, phone);
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro de la catalogo");
        }
    }
}
