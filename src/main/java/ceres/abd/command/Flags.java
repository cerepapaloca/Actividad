/**
 * Código copiado de otros proyectos
 */
package ceres.abd.command;

import ceres.abd.Utils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * El comando usa los flags. Para añadir un flag se tiene que crear un {@link Flag} y añadirlo en {@link #getFlags()}
 *
 * @see CommandFlag
 */

public interface Flags {

    List<Flag> getFlags();

    default List<String> getNameFlags(){
        return getFlags().stream().map(Flag::getName).collect(Collectors.toList());
    }

    @Nullable
    default Flag getFlag(String name) {
        return getNameFlags().contains(name) ? getFlags().get(getNameFlags().indexOf(name)) : null;
    }

    /**
     * Es una referencia para crear luego un {@link CommandFlag} en caso de que el jugador añada un valor
     */

    @Getter
    class Flag {
        private final @NotNull String name;
        private final @NotNull TypeValue value;

        /**
         * Los argumentos cuando se usa {@code STRING} en {@link TypeValue}
         */

        private final String[] args;

        public Flag(@NotNull String name, @NotNull TypeValue value) {
            this.name = name;
            this.value = value;
            this.args = new String[0];
        }
        public Flag(@NotNull String name, @NotNull TypeValue value, String... args) {
            this.name = name;
            this.value = value;
            this.args = args;
        }
        public Flag(@NotNull String name, @NotNull TypeValue value, Enum<?>[] args) {
            this.name = name;
            this.value = value;
            this.args = Utils.enumsToStrings(args);
        }
    }

    enum TypeValue{
        BOOLEAN,
        INTEGER,
        STRING,
        FLOAT,
    }

}
