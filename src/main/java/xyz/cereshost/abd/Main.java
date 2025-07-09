package xyz.cereshost.abd;

import lombok.Getter;
import lombok.Setter;
import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import xyz.cereshost.abd.command.CommandHandler;
import xyz.cereshost.abd.command.commands.*;
import xyz.cereshost.abd.storage.sql.*;

import java.io.IOException;
import java.util.Arrays;
public class Main {

    @Getter
    private static final CommandHandler commandHandler = new CommandHandler();
    @Setter
    @Getter
    private static boolean isRunning = true;

    public static final CatalogDataBase CATALOG_DATA_BASE = new CatalogDataBase();
    public static final ClientDataBase CLIENT_DATA_BASE = new ClientDataBase();
    public static final InventoryCarDataBase INVENTORY_CAR_DATA_BASE = new InventoryCarDataBase();
    public static final InventoryPiecesDataBase INVENTORY_PIECES_DATA_BASE = new InventoryPiecesDataBase();
    public static final SellerDataBase SELLER_DATA_BASE = new SellerDataBase();
    public static final SellLogDataBase SELL_LOG_DATA_BASE = new SellLogDataBase();

    static{
        commandHandler.registerCommand(
                new HelpCommand(),
                new ExitCommand(),
                new ShowCommand(),
                new AddCommand(),
                new DeleteCommand()
        );
    }

    public static void main(String[] a) throws IOException {
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        Service.sendMessage("Bienvenido. Usa help para ver todos los comandos\n");

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .build();

        while (isRunning) {
            String line;
            try {
                line = reader.readLine("> ");
                String[] args = line.split(" ");
                try {
                    String repose = commandHandler.onCommand(args[0], Arrays.copyOfRange(args, 1, args.length));
                    if (repose != null) {
                        System.out.println(repose);
                    }
                } catch (Exception e) {
                    Service.sendMessage("Error al ejecutar el comando: " + args[0], e);
               }
            } catch (UserInterruptException | EndOfFileException e) {
                break;
            }
        }
    }
}