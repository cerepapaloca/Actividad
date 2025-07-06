package xyz.cereshost.abd.storage.sql;

import xyz.cereshost.abd.storage.DataBaseMySql;

public class Catalog extends DataBaseMySql {
    @Override
    protected String getSqlTable() {
        return """
                CREATE TABLE `Catalogo` (
                  `modelo` varchar(30) PRIMARY KEY,
                  `fabricante` varchar(30),
                  `cilidrada` varchar(30),
                  `precio` varchar(30)
                );
                """;
    }
}
