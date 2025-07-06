package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.storage.DataBaseMySql;

public class Client extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `Cliente` (
                  `dni` varchar(255) PRIMARY KEY,
                  `nombre` varchar(64) NOT NULL,
                  `direccion` varchar(30),
                  `telefono` varchar(30)
                );
                """;
    }
}
