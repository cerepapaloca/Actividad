package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Catalog extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `Catalogo` (
                  `modelo` varchar(30) PRIMARY KEY,
                  `fabricante` varchar(30),
                  `cilidrada` varchar(30),
                  `precio` varchar(30)
                );
                """;
    }

    public static void addRow(String modelo, String fabricante, String cilidrada, long precio) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO Catalogo(modelo, fabricante, cilidrada, precio) " +
                    "VALUES (?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelo);
            statement.setString(2, fabricante);
            statement.setString(3, cilidrada);
            statement.setLong(4, precio);
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro de la catalogo");
        }
    }
}
