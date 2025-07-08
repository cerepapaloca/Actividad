package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryPieces extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `InvetarioPiezas` (
                  `Serie` bigint PRIMARY KEY,
                  `fecha` timestamp
                );
                """;
    }

    public static void addRow(long serial) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO InvetarioPiezas(matricula) " +
                    "VALUES (?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, serial);
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro de la catalogo");
        }
    }
}
