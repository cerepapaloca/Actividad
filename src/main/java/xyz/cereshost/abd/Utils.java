package xyz.cereshost.abd;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.cereshost.abd.command.Command;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@UtilityClass
public class Utils {
    @NotNull
    @Contract(pure = true)
    public String[] enumsToStrings(Enum<?> @NotNull [] raw){
        String[] strings = new String[raw.length];
        int i = 0 ;
        for (Enum<?> e : raw){
            strings[i] = e.name().toLowerCase();
            i++;
        }
        return strings;
    }


    private int getMax(@NotNull Iterable<String> strings){

        int length = 0;
        for (String s : strings) length++;

        int[] ints = new int[length];
        int i = 0 ;
        for (String s : strings){
            ints[i++] = s.length();
        }
        return Arrays.stream(ints).max().orElse(0);
    }

    public @NotNull String applySpace(int margin, @NotNull String s, Iterable<String>  strings){
        return s + " ".repeat(Math.max(0, (Math.max(getMax(strings), s.length()) + margin) - s.length()));
    }

    public String dateToString(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }


}
