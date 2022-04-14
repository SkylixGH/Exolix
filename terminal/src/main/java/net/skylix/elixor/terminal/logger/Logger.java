package net.skylix.elixor.terminal.logger;

import java.sql.Timestamp;

/**
 * A class used for printing messages to the terminal.
 */
public class Logger {
    private static Logger baseLogger;

    private final LoggerSettings settings;

    public Logger() {
        this(new LoggerSettings());
    }

    public Logger(LoggerSettings settings) {
        this.settings = settings;
    }

    private void logWithPrefix(String message, LoggerTypes type) {
        String padding = " ".repeat(settings.padding);

        String prefixLabel = switch (type) {
            case SUCCESS -> settings.prefixTextSurrounds[0] +
                    padding +
                    settings.successText +
                    padding +
                    settings.prefixTextSurrounds[1];
            case WARNING -> settings.prefixTextSurrounds[0] +
                    padding +
                    settings.warningText +
                    padding +
                    settings.prefixTextSurrounds[1];
            case ERROR -> settings.prefixTextSurrounds[0] +
                    padding +
                    settings.errorText +
                    padding +
                    settings.prefixTextSurrounds[1];
            case DEPRECATED -> settings.prefixTextSurrounds[0] +
                    padding +
                    settings.deprecatedText +
                    padding +
                    settings.prefixTextSurrounds[1];
            case DEBUG -> settings.prefixTextSurrounds[0] +
                    padding +
                    settings.debugText +
                    padding +
                    settings.prefixTextSurrounds[1];
            case VERBOSE -> settings.prefixTextSurrounds[0] +
                    padding +
                    settings.verboseText +
                    padding +
                    settings.prefixTextSurrounds[1];
            default -> settings.prefixTextSurrounds[0] +
                    padding +
                    settings.infoText +
                    padding +
                    settings.prefixTextSurrounds[1];
        };

        String timeStamp = "";

        if (settings.useTimeStamps) {
            timeStamp = settings.prefixTimeSurrounds[0] +
                    padding +
                    (new Timestamp(System.currentTimeMillis())) +
                    padding +
                    settings.prefixTimeSurrounds[1];

        }

        System.out.println((!settings.useTimeStamps ? "" : (timeStamp + padding)) + prefixLabel + padding + message);
    }

    public void info(String message) {
        logWithPrefix(message, LoggerTypes.INFO);
    }

    public void success(String message) {
        logWithPrefix(message, LoggerTypes.SUCCESS);
    }

    public void warning(String message) {
        logWithPrefix(message, LoggerTypes.WARNING);
    }

    public void error(String message) {
        logWithPrefix(message, LoggerTypes.ERROR);
    }

    public void debug(String message) {
        logWithPrefix(message, LoggerTypes.DEBUG);
    }

    public void verbose(String message) {
        logWithPrefix(message, LoggerTypes.VERBOSE);
    }

    public void deprecated(String message) {
        logWithPrefix(message, LoggerTypes.DEPRECATED);
    }

    private static Logger setupBaseLogger() {
        if (baseLogger == null) {
            baseLogger = new Logger();
        }

        return baseLogger;
    }

    public static void infoBase(String message) {
        setupBaseLogger().info(message);
    }

    public static void successBase(String message) {
        setupBaseLogger().success(message);
    }

    public static void warningBase(String message) {
        setupBaseLogger().warning(message);
    }

    public static void errorBase(String message) {
        setupBaseLogger().error(message);
    }

    public static void debugBase(String message) {
        setupBaseLogger().debug(message);
    }

    public static void verboseBase(String message) {
        setupBaseLogger().verbose(message);
    }

    public static void deprecatedBase(String message) {
        setupBaseLogger().deprecated(message);
    }
}
