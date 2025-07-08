package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.model.LogSellData;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SellLogDataBase extends DataBaseMySql<LogSellData> {

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

    @Override
    public LogSellData getData(String s) {
        try (Connection connection = getConnection()){
            String sql = "SELECT * FROM `Vendedor` WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            return new LogSellData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getTime(6).getTime(), resultSet.getLong(7));
        } catch (SQLException e) {
            Service.sendMessage("Error al leer el registro", e);
        }
        return null;
    }

    @Override
    public List<LogSellData> getAll() {
        String sql = "SELECT * FROM `Vendedor`";
        List<LogSellData> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                list.add(new LogSellData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getTime(6).getTime(), resultSet.getLong(7)));
            }
        }catch (SQLException e) {
            Service.sendMessage("Error al leer todos los registros", e);
        }
        return list;
    }

    @Override
    public void addRow(LogSellData data) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO `Ventas`(id, model, cliente, Vendedor, Matricula, precio) " +
                    "VALUES (?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, data.model());
            statement.setString(3, data.cliente());
            statement.setString(4, data.Vendedor());
            statement.setString(5, data.Matricula());
            statement.setLong(6, data.precio());
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro", e);
        }
    }
}
