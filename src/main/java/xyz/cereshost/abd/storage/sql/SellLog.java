package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.storage.DataBaseMySql;

public class SellLog extends DataBaseMySql {
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
}
