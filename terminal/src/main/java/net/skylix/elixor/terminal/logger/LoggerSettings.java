package net.skylix.elixor.terminal.logger;

/**
 * Settings for the logger.
 */
public class LoggerSettings {
    /**
     * Whether to render a timestamp before every logged message.
     */
    public boolean useTimeStamps = true;

    /**
     * Text for informative messages.
     */
    public String infoText = "info";

    /**
     * Text for warning messages.
     */
    public String warningText = "warning";

    /**
     * Text for error messages.
     */
    public String errorText = "error";

    /**
     * Text for debug messages.
     */
    public String debugText = "debug";

    /**
     * Text for success messages.
     */
    public String successText = "success";

    /**
     * Text for verbose messages.
     */
    public String verboseText = "verbose";

    /**
     * Text for deprecated messages.
     */
    public String deprecatedText = "deprecated";

    /**
     * Padding between text, surrounds and prefixes.
     */
    public Integer padding = 1;

    /**
     * Text to surround the indicator prefix.
     */
    public String[] prefixTextSurrounds = { "[", "]:" };

    /**
     * Text that surrounds the time stamps.
     */
    public String[] prefixTimeSurrounds = { "[", "]" };
}
