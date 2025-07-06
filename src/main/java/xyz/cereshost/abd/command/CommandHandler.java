package xyz.cereshost.abd.command;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CommandHandler {

    private final HashSet<String> namesKnown = new HashSet<>();
    private final HashMap<String, Command> commands = new HashMap<>();

    public String onCommand(String name, String[] args) {
        Command command = commands.get(name);
        if (command != null) {
            command.execute(Arguments.createWithFlags(command, args));
            return null;
        }else {
            return "Command not found";
        }
    }

    public void registerCommand(Command @NotNull ... command) {
        for (Command com : command) {
            commands.put(com.getCommand(), com);
            for (String alises : com.getAliases()) {
                commands.put(alises, com);
            }
            namesKnown.add(com.getCommand());
        }
    }

    public List<Command> getCommandsKnown() {
        return namesKnown.stream().map(commands::get).toList();
    }
}
