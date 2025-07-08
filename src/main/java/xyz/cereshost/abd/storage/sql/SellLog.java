package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SellLog extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `Ventas` (
                  `id` vachar(36) PRIMARY KEY,
                  `model` varchar(30) NOT NULL,
                  `cliente` varchar(255) NOT NULL,
                  `Vendedor` varchar(255) NOT NULL,
                  `Matricula` varcahr(10) NOT NULL,
                  `fecha` timestamp,
                  `precio` bigint
                );
                """;
    }

    public static void addRow(String name, String model, String cliente, String vendedor, String matricula, long precio) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO Ventas(id, model, cliente, Vendedor, Matricula, precio) " +
                    "VALUES (?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, model);
            statement.setString(3, cliente);
            statement.setString(4, vendedor);
            statement.setString(5, matricula);
            statement.setLong(6, precio);
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro de la catalogo");
        }
    }
}
