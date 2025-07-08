package xyz.cereshost.abd;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Service {

    public void sendMessage(String log, String... args) {
        System.out.printf(log + "\n", (Object[]) args);
    }

    public void sendMessage(String log, @NotNull Exception e, String... args) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement traceElement : e.getStackTrace()) {
            builder.append(traceElement.toString()).append("\n\t");
        }
        System.err.printf(log + " [" + e.getCause() + "=" + e.getMessage() + "]" + builder, (Object[]) args);
    }
}
