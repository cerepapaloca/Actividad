package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "muestras todos los elementos de una categoría");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0)){
            case "muestras":
        }
    }
}
