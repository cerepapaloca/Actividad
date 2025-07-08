package xyz.cereshost.abd.storage.sql;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.model.CatalogData;
import xyz.cereshost.abd.storage.DataBaseMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogDataBase extends DataBaseMySql<CatalogData> {
    public CatalogDataBase() {
        super();
    }

    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE IF NOT EXISTS `Catalogo` (
                  `modelo` varchar(30) PRIMARY KEY,
                  `manufacter` varchar(30),
                  `displacement` varchar(30),
                  `precio` varchar(30)
                );
                """;
    }

    @Override
    public CatalogData getData(@NotNull String s) {
        try (Connection connection = getConnection()){
            String sql = "SELECT * FROM `Catalogo` WHERE modelo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            return new CatalogData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
        } catch (SQLException e) {
            Service.sendMessage("Error al leer el registro", e);
        }
        return null;
    }

    @Contract(" -> new")
    @Override
    public List<CatalogData> getAll() {
        String sql = "SELECT * FROM `Catalogo`";
        List<CatalogData> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                list.add(new CatalogData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4)));
            }
        }catch (SQLException e) {
            Service.sendMessage("Error al leer todos los registros", e);
        }
        return list;
    }

    public void addRow(@NotNull CatalogData catalogData) {
        try (Connection connection = getConnection()){
            String sql = "INSERT INTO `Catalogo`(modelo, fabricante, cilidrada, precio) " +
                    "VALUES (?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, catalogData.model());
            statement.setString(2, catalogData.manufacturer());
            statement.setString(3, catalogData.displacement());
            statement.setLong(4, catalogData.price());
            statement.executeUpdate();
        } catch (SQLException e) {
            Service.sendMessage("Error al agregar el registro de la catalogo", e);
        }
    }

    @Override
    public void removeRow(String value) {
        String sql = "DELETE FROM `Catalogo` WHERE modelo = ?";

        try (Connection connection = getConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            statement.execute();

        } catch (SQLException e) {
            Service.sendMessage("Error al eliminar una fila", e);
        }
    }
}
