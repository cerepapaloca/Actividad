package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.model.SellerData;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDataBase extends DataBaseMySql<SellerData> {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `Vendedor` (
                  `dni` varchar(255) PRIMARY KEY,
                  `nombre` varchar(64) NOT NULL,
                  `address` varchar(30),
                  `telephone` varchar(30)
                );
                """;
    }

    @Override
    public SellerData getData(String s) {
        try (Connection connection = getConnection()){
            String sql = "SELECT * FROM `Vendedor` WHERE dni = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            return new SellerData(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), Integer.parseInt(resultSet.getString(4)));
        } catch (SQLException e) {
            Service.sendMessage("Error al leer el registro", e);
        }
        return null;
    }

    @Override
    public List<SellerData> getAll() {
        String sql = "SELECT * FROM `Vendedor`";
        List<SellerData> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                list.add(new SellerData(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), Integer.parseInt(resultSet.getString(4))));
            }
        }catch (SQLException e) {
            Service.sendMessage("Error al leer todos los registros", e);
        }
        return list;
    }

    @Override
    public void addRow(SellerData data) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO `Vendedor`(dni, nombre, address, telephone) " +
                    "VALUES (?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(data.getDni()));
            statement.setString(2, data.getName());
            statement.setString(3, data.getAddress());
            statement.setString(4, String.valueOf(data.getTelephone()));
            statement.executeUpdate();
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro", e);
        }
    }
}
