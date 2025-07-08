package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.Main;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;
import xyz.cereshost.abd.model.*;

import java.util.UUID;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        super("delete", "borras una fila en la base de datos.");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0).toLowerCase()){
            case "catalogo" -> {
                if (arg.length() >= 2){
                    Main.CATALOG_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "car" -> {
                if (arg.length() >= 2){
                    Main.INVENTORY_CAR_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "pieces" -> {
                if (arg.length() >= 2){
                    Main.INVENTORY_PIECES_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "cliente" -> {
                if (arg.length() >= 2){
                    Main.CLIENT_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "vendedor" -> {
                if (arg.length() >= 2){
                    Main.SELLER_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "log" -> {
                if (arg.length() >= 2){
                    Main.SELL_LOG_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            default -> Service.sendMessage("Usa al menos <log> <vendedor> <cliente> <pieces> <car> <catalogo>");
        }
    }
}
