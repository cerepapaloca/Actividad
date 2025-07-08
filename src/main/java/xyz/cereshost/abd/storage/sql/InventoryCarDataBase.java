package xyz.cereshost.abd.storage.sql;

import org.jetbrains.annotations.NotNull;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.model.CarData;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryCarDataBase extends DataBaseMySql<CarData> {

    public InventoryCarDataBase() {
        super();
    }

    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE IF NOT EXISTS `InvetarioCarros` (
                  `matricula` varchar(30) PRIMARY KEY,
                  `model` varchar(30) NOT NULL,
                  `paint` varchar(30),
                  `manufacter` varchar(30),
                  `displacement` varchar(30),
                  `price` bigint
                );
                """;
    }

    @Override
    public CarData getData(String s) {
        try (Connection connection = getConnection()){
            String sql = "SELECT * FROM `InvetarioCarros` WHERE matricula = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            return new CarData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6));
        } catch (SQLException e) {
            Service.sendMessage("Error al leer el registro", e);
        }
        return null;
    }

    @Override
    public List<CarData> getAll() {
        String sql = "SELECT * FROM `InvetarioCarros`";
        List<CarData> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                list.add(new CarData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6)));
            }
        }catch (SQLException e) {
            Service.sendMessage("Error al leer todos los registros", e);
        }
        return list;
    }

    @Override
    public void addRow(@NotNull CarData data) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO `InvetarioCarros`(matricula, model, paint, manufacter, displacement, price) " +
                    "VALUES (?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, data.tuition());
            statement.setString(2, data.model());
            statement.setString(3, data.paint());
            statement.setString(4, data.manufacturer());
            statement.setString(5, data.displacement());
            statement.setLong(6, data.price());
            statement.executeUpdate();
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro", e);
        }
    }

    @Override
    public void removeRow(String value) {
        String sql = "DELETE FROM `InvetarioCarros` WHERE matricula = ?";

        try (Connection connection = getConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            statement.execute();

        } catch (SQLException e) {
            Service.sendMessage("Error al eliminar una fila", e);
        }
    }
}