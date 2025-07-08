package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.Main;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;
import xyz.cereshost.abd.model.*;

import java.util.UUID;

public class AddCommand extends Command {
    public AddCommand(){
        super("add", "Añades un registro de algo");
    }

    @Override
    public void execute(Arguments arg) {

    }
}
