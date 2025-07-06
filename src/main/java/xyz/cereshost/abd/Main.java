package xyz.cereshost.abd;

import lombok.Getter;
import lombok.Setter;
import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import xyz.cereshost.abd.command.Command;
import xyz.cereshost.abd.command.CommandHandler;
import xyz.cereshost.abd.command.commands.ExitCommand;
import xyz.cereshost.abd.command.commands.HelpCommand;

import java.io.IOException;
import java.util.Arrays;
public class Main {

    @Getter
    private static final CommandHandler commandHandler = new CommandHandler();
    @Setter
    @Getter
    private static boolean isRunning = true;

    static{
        commandHandler.registerCommand(
                new HelpCommand(),
                new ExitCommand()
        );
    }

    public static void main(String[] a) throws IOException {
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        Completer completer = new StringsCompleter("start", "stop", "status", "restart");

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completer)
                .parser(new DefaultParser())
                .build();

        while (isRunning) {
            String line;
            try {
                line = reader.readLine("> ");
                String[] args = line.split(" ");
                String repose = commandHandler.onCommand(args[0], Arrays.copyOfRange(args, 1, args.length));
                if (repose != null) {
                    System.out.println(repose);
                }
            } catch (UserInterruptException | EndOfFileException e) {
                break;
            }
        }
    }
}