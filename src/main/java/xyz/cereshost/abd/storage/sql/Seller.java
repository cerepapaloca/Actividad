package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.storage.DataBaseMySql;

public class Seller extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `Vendedor` (
                  `dni` varchar(255) PRIMARY KEY,
                  `nombre` varchar(64) NOT NULL,
                  `direccion` varchar(30),
                  `telefono` varchar(30)
                );
                """;
    }
}
