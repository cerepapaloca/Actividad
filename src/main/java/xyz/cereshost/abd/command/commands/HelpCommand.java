package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.Main;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.Utils;
import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;

import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "Puedes ver todos los comandos");
        addAlise("h");
    }

    @Override
    public void execute(Arguments arg ) {

        StringBuilder builder = new StringBuilder();
        List<Command> commands = Main.getCommandHandler().getCommandsKnown();
        List<String> names = commands.stream().map(Command::getCommand).toList();
        List<String> description = commands.stream().map(Command::getDescription).toList();

        for (Command command : commands) {
            builder.append("\t").append(Utils.applySpace(2, command.getCommand(), names)).append("|").append(Utils.applySpace(0, command.getDescription(), description)).append("\n");
        }


        Service.sendMessage(builder.toString());
    }
}
