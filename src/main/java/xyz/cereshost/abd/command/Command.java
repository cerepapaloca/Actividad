package xyz.cereshost.abd.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class Command {

    private final String command;
    private final String description;
    private String[] aliases = new String[0];

    public void addAlise(String... alias) {
        List<String> aliasesList = new ArrayList<>(Arrays.asList(alias));
        aliasesList.addAll(Arrays.asList(aliases));
        aliases = aliasesList.toArray(new String[0]);
    }

    public abstract void execute(Arguments arg);
}
