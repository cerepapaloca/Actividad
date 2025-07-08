package xyz.cereshost.abd.storage.sql;

import org.jetbrains.annotations.NotNull;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.model.CarData;
import xyz.cereshost.abd.model.PiecesData;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryPiecesDataBase extends DataBaseMySql<PiecesData> {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `InvetarioPiezas` (
                  `Serie` bigint PRIMARY KEY,
                  `fecha` timestamp
                );
                """;
    }

    @Override
    public PiecesData getData(String s) {
        try (Connection connection = getConnection()){
            String sql = "SELECT * FROM `InvetarioPiezas` WHERE Serie = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            return new PiecesData(resultSet.getLong(1), resultSet.getTimestamp(2).getTime());
        } catch (SQLException e) {
            Service.sendMessage("Error al leer el registro", e);
        }
        return null;
    }

    @Override
    public List<PiecesData> getAll() {
        String sql = "SELECT * FROM `InvetarioPiezas`";
        List<PiecesData> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                list.add(new PiecesData(resultSet.getLong(1), resultSet.getTimestamp(2).getTime()));
            }
        }catch (SQLException e) {
            Service.sendMessage("Error al leer todos los registros", e);
        }
        return list;
    }

    @Override
    public void addRow(@NotNull PiecesData data) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO `InvetarioPiezas`(Serie) " +
                    "VALUES (?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, data.id());
            statement.executeUpdate();
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro", e);
        }
    }
}
