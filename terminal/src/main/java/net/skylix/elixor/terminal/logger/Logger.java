package net.skylix.elixor.terminal.logger;

import java.sql.Timestamp;

/**
 * A class used for printing messages to the terminal.
 */
public class Logger {
    /**
     * The default logger instance.
     */
    private static Logger baseLogger;

    /**
     * Settings for the logger and its configuration/
     */
    private final LoggerSettings settings;

    /**
     * Create a new logger instance.
     */
    public Logger() {
        this(new LoggerSettings());
    }

    /**
     * Create a new logger instance with the given settings.
     *
     * @param settings Settings to configure the behavior of the logger.
     */
    public Logger(LoggerSettings settings) {
        this.settings = settings;
    }

    /**
     * Create and configure the default static logger.
     *
     * @return The default static logger.
     */
    private static Logger setupBaseLogger() {
        if (baseLogger == null) {
            baseLogger = new Logger();
        }

        return baseLogger;
    }

    /**
     * Log an informative message with the default logger.
     *
     * @param message The message to log.
     */
    public static void infoBase(String message) {
        setupBaseLogger().info(message);
    }

    /**
     * Log a message indicating a successful operation via the default logger.
     *
     * @param message The message to log.
     */
    public static void successBase(String message) {
        setupBaseLogger().success(message);
    }

    /**
     * Log a message warning the user of a potential problem via the default logger.
     *
     * @param message The message to log.
     */
    public static void warningBase(String message) {
        setupBaseLogger().warning(message);
    }

    /**
     * Log a message indicating an error has occurred via the default logger.
     *
     * @param message The message to log.
     */
    public static void errorBase(String message) {
        setupBaseLogger().error(message);
    }

    /**
     * Log a message for debug usage via the default logger.
     *
     * @param message The message to log.
     */
    public static void debugBase(String message) {
        setupBaseLogger().debug(message);
    }

    /**
     * Log a message for verbose actions via the default logger.
     *
     * @param message The message to log.
     */
    public static void verboseBase(String message) {
        setupBaseLogger().verbose(message);
    }

    /**
     * Log a message indicating deprecation of a feature via the default logger.
     *
     * @param message The message to log.
     */
    public static void deprecatedBase(String message) {
        setupBaseLogger().deprecated(message);
    }

    /**
     * Log a message with a prefix type.
     *
     * @param message The message to log.
     * @param type    The type of the message.
     */
    private void logWithPrefix(String message, LoggerTypes type) {
        String padding = " ".repeat(settings.padding);

        String prefixLabel = switch (type) {
            case SUCCESS -> settings.prefixTextSurrounds[0]
                    + padding
                    + settings.successText
                    + padding
                    + settings.prefixTextSurrounds[1];
            case WARNING -> settings.prefixTextSurrounds[0]
                    + padding
                    + settings.warningText
                    + padding
                    + settings.prefixTextSurrounds[1];
            case ERROR -> settings.prefixTextSurrounds[0]
                    + padding
                    + settings.errorText
                    + padding
                    + settings.prefixTextSurrounds[1];
            case DEPRECATED -> settings.prefixTextSurrounds[0]
                    + padding
                    + settings.deprecatedText
                    + padding
                    + settings.prefixTextSurrounds[1];
            case DEBUG -> settings.prefixTextSurrounds[0]
                    + padding
                    + settings.debugText
                    + padding
                    + settings.prefixTextSurrounds[1];
            case VERBOSE -> settings.prefixTextSurrounds[0]
                    + padding
                    + settings.verboseText
                    + padding
                    + settings.prefixTextSurrounds[1];
            default -> settings.prefixTextSurrounds[0]
                    + padding
                    + settings.infoText
                    + padding
                    + settings.prefixTextSurrounds[1];
        };

        String timeStamp = "";

        if (settings.useTimeStamps) {
            timeStamp = settings.prefixTimeSurrounds[0]
                    + padding
                    + (new Timestamp(System.currentTimeMillis()))
                    + padding
                    + settings.prefixTimeSurrounds[1];

        }

        System.out.println((!settings.useTimeStamps ? "" : (timeStamp + padding)) + prefixLabel + padding + message);
    }

    /**
     * Log an informative message.
     *
     * @param message The message to log.
     */
    public void info(String message) {
        logWithPrefix(message, LoggerTypes.INFO);
    }

    /**
     * Log a message indicating a successful operation.
     *
     * @param message The message to log.
     */
    public void success(String message) {
        logWithPrefix(message, LoggerTypes.SUCCESS);
    }

    /**
     * Log a message warning the user of a potential problem.
     *
     * @param message The message to log.
     */
    public void warning(String message) {
        logWithPrefix(message, LoggerTypes.WARNING);
    }

    /**
     * Log a message indicating an error has occurred.
     *
     * @param message The message to log.
     */
    public void error(String message) {
        logWithPrefix(message, LoggerTypes.ERROR);
    }

    /**
     * Log a message for debug usage.
     *
     * @param message The message to log.
     */
    public void debug(String message) {
        logWithPrefix(message, LoggerTypes.DEBUG);
    }

    /**
     * Log a message for verbose actions.
     *
     * @param message The message to log.
     */
    public void verbose(String message) {
        logWithPrefix(message, LoggerTypes.VERBOSE);
    }

    /**
     * Log a message indicating deprecation of a feature.
     *
     * @param message The message to log.
     */
    public void deprecated(String message) {
        logWithPrefix(message, LoggerTypes.DEPRECATED);
    }
}
