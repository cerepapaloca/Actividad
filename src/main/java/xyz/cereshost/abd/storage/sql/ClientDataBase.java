package xyz.cereshost.abd.storage.sql;

import org.jetbrains.annotations.NotNull;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.model.ClientData;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDataBase extends DataBaseMySql<ClientData> {
    public ClientDataBase() {
        super();
    }

    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE IF NOT EXISTS `Cliente` (
                  `dni` varchar(255) PRIMARY KEY,
                  `nombre` varchar(64) NOT NULL,
                  `direccion` varchar(30),
                  `telefono` varchar(30)
                );
                """;
    }

    @Override
    public ClientData getData(String s) {
        try (Connection connection = getConnection()){
            String sql = "SELECT * FROM `Cliente` WHERE dni = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            return new ClientData(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), Integer.parseInt(resultSet.getString(4)));
        } catch (SQLException e) {
            Service.sendMessage("Error al leer el registro", e);
        }
        return null;
    }

    @Override
    public List<ClientData> getAll() {
        String sql = "SELECT * FROM `Cliente`";
        List<ClientData> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                list.add(new ClientData(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), Integer.parseInt(resultSet.getString(4))));
            }
        }catch (SQLException e) {
            Service.sendMessage("Error al leer todos los registros", e);
        }
        return list;
    }

    @Override
    public void addRow(@NotNull ClientData data) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO `Cliente`(dni, nombre, direccion, telefono) " +
                    "VALUES (?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(data.getDni()));
            statement.setString(2, data.getName());
            statement.setString(3, data.getAddress());
            statement.setLong(4, data.getTelephone());
            statement.executeUpdate();
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro", e);
        }
    }

    @Override
    public void removeRow(String value) {
        String sql = "DELETE FROM `Cliente` WHERE dni = ?";

        try (Connection connection = getConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            statement.execute();

        } catch (SQLException e) {
            Service.sendMessage("Error al eliminar una fila", e);
        }
    }
}
