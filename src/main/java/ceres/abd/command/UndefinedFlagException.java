/**
 * Código copiado de otros proyectos
 */
package ceres.abd.command;

/**
 * Se usa cuando se intenta obtener un flag, pero este no se escribió en los argumentos
 */
public class UndefinedFlagException extends Exception {
    public UndefinedFlagException(String message) {
        super(message);
    }
}
