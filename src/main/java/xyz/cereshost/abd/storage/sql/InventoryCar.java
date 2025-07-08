package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryCar extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `InvetarioCarros` (
                  `matricula` varchat(30) PRIMARY KEY,
                  `model` varchar(30) NOT NULL,
                  `pintura` varchar(30),
                  `fabricante` varchar(30),
                  `cilidrada` varchar(30),
                  `precio` bigint
                );
                """;
    }

    public static void addRow(String tuition, String model, String pintura,  String fabricante, String cilidrada, long precio) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO InvetarioCarros(matricula, model, pintura, fabricante, cilidrada, precio) " +
                    "VALUES (?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tuition);
            statement.setString(2, model);
            statement.setString(3, pintura);
            statement.setString(4, fabricante);
            statement.setString(5, cilidrada);
            statement.setLong(6, precio);
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro de la catalogo");
        }
    }
}