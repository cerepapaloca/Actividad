package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.storage.DataBaseMySql;

public class InventoryPieces extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `InvetarioPiezas` (
                  `Serie` bigint PRIMARY KEY,
                  `fecha` timestamp
                );
                """;
    }
}
