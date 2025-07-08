package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;
import xyz.cereshost.abd.storage.sql.CatalogDataBase;
import xyz.cereshost.abd.storage.sql.ClientDataBase;

public class addRegister extends Command {
    public addRegister(){
        super("addRegister", "Añades un registro de algo");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0)){
            case "catalogo" -> {
                if (arg.length() >= 4){
                    CatalogDataBase.addRow(arg.get(1), arg.get(2), arg.get(3) , Long.parseLong(arg.get(4)));
                }else {
                    Service.sendMessage("Tienes que añadir un [Modelo] [Fabricante] [Cilidrada] [Precio]");
                }
            }

            //String dni, String name, String address, String phone

            case "inventoryCar" -> {
                if (arg.length() >= 4){
                    ClientDataBase.addRow(arg.get(1), arg.get(2), arg.get(3) , arg.get(4));
                }else {
                    Service.sendMessage("Tienes que añadir un [dni] [name] [address] [phone]");
                }
            }

            case "cliente" -> {

            }
        }
    }
}
