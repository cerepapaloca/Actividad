package ceres.abd;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Service {

    public void sendLog(String log, String... args) {
        System.out.printf(log, (Object[]) args);
    }

    public void sendLog(String log, Exception e, String... args) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement traceElement : e.getStackTrace()) {
            builder.append(traceElement.toString()).append("\n\t");
        }
        System.err.printf(log + "[" + e.getCause() + "=" + e.getMessage() + "]" + builder, (Object[]) args);
    }
}
