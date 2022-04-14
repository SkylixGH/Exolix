package net.skylix.elixor.terminal.logger;

/**
 * Settings for the logger.
 */
public class LoggerSettings {
    public boolean useTimeStamps = true;

    public String infoText = "info";

    public String warningText = "warning";

    public String errorText = "error";

    public String debugText = "debug";

    public String successText = "success";

    public String verboseText = "verbose";

    public String deprecatedText = "deprecated";

    public Integer padding = 2;

    public String[] prefixTextSurrounds = { "[", "]" };

    public String[] prefixTimeSurrounds = { "[", "]" };
}
