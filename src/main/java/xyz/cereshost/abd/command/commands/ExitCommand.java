package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.Main;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "terminas el proceso");
        addAlise("stop");
    }

    @Override
    public void execute(Arguments arg) {
        Main.setRunning(false);
        Service.sendMessage("Bye");
    }
}
