package xyz.cereshost.abd;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

}
