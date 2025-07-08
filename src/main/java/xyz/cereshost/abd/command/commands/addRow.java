package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.Main;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;
import xyz.cereshost.abd.model.*;

import java.util.UUID;

public class addRow extends Command {
    public addRow(){
        super("addRow", "Añades un registro de algo");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0).toLowerCase()){
            case "catalogo" -> {
                if (arg.length() >= 4){
                    Main.CATALOG_DATA_BASE.addRow(new CatalogData(arg.get(1), arg.get(2), arg.get(3) , Long.parseLong(arg.get(4))));
                }else {
                    Service.sendMessage("Tienes que añadir [Modelo] [Fabricante] [Cilidrada] [Precio]");
                }
            }
            case "car" -> {
                if (arg.length() >= 6){
                    Main.INVENTORY_CAR_DATA_BASE.addRow(new CarData(arg.get(1), arg.get(2), arg.get(3), arg.get(4), arg.get(5), Long.parseLong(arg.get(6))));
                }else {
                    Service.sendMessage("Tienes que añadir [matricula] [modelo] [paint] [fabricante] [cilindrada] [precio]");
                }
            }
            case "pieces" -> {
                if (arg.length() >= 1){
                    Main.INVENTORY_PIECES_DATA_BASE.addRow(new PiecesData(Long.parseLong(arg.get(1)), System.currentTimeMillis()));
                }else {
                    Service.sendMessage("Tienes que añadir [serial]");
                }
            }
            case "cliente" -> {
                if (arg.length() >= 4){
                    Main.CLIENT_DATA_BASE.addRow(new ClientData(Integer.parseInt(arg.get(1)), arg.get(2), arg.get(3), Integer.parseInt(arg.get(4))));
                }else {
                    Service.sendMessage("Tienes que añadir [dni] [nombre] [dirección] [teléfono]");
                }
            }
            case "vendedor" -> {
                if (arg.length() >= 4){
                    Main.SELLER_DATA_BASE.addRow(new SellerData(Integer.parseInt(arg.get(1)), arg.get(2), arg.get(3), Integer.parseInt(arg.get(4))));
                }else {
                    Service.sendMessage("Tienes que añadir [dni] [nombre] [dirección] [teléfono]");
                }
            }
            case "log" -> {
                if (arg.length() >= 4){
                    Main.SELL_LOG_DATA_BASE.addRow(new LogSellData(UUID.randomUUID().toString(), arg.get(1), arg.get(2), arg.get(3), arg.get(4), Long.parseLong(arg.get(5)), Long.parseLong(arg.get(6))));
                }else {
                    Service.sendMessage("Tienes que añadir [modelo] [cliente] [vendedor] [matricula] [precio]");
                }
            }
        }
    }
}
