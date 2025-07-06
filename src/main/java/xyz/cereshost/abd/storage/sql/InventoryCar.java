package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.storage.DataBaseMySql;

public class InventoryCar extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `InvetarioCarros` (
                  `matricula` varchat(30) PRIMARY KEY,
                  `model` varchar(30) NOT NULL,
                  `pintura` varchar(30),
                  `fabricante` varchar(30),
                  `cilidrada` varchar(30),
                  `precio` bigint
                );
                """;
    }
}