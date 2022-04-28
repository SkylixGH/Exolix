package net.skylix.elixor.terminal.logger;

import net.skylix.elixor.terminal.ansiChain.AnsiChain;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.function.Supplier;

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
    public String infoText;

    /**
     * Text for warning messages.
     */
    public String warningText;

    /**
     * Text for error messages.
     */
    public String errorText;

    /**
     * Text for debug messages.
     */
    public String debugText;

    /**
     * Text for success messages.
     */
    public String successText;

    /**
     * Text for verbose messages.
     */
    public String verboseText;

    /**
     * Text for deprecated messages.
     */
    public String deprecatedText;

    /**
     * Padding between text, surrounds and prefixes.
     */
    public Integer padding = 1;

    /**
     * Text to surround the indicator prefix.
     */
    public String[] prefixTextSurrounds;

    /**
     * Text that surrounds the time stamps.
     */
    public String[] prefixTimeSurrounds;

    /**
     * The internal constructor.
     */
    public LoggerSettings() {
        try {
            infoText = new AnsiChain().color("#60cdff").apply("info");
            errorText = new AnsiChain().color("#f55").apply("error");
            warningText = new AnsiChain().color("#ff5").apply("warning");
            debugText = new AnsiChain().color("#fcbe03").apply("debug");
            successText = new AnsiChain().color("#50ffab").apply("success");
            verboseText = new AnsiChain().color("#fcbe03").apply("verbose");
            deprecatedText = new AnsiChain().color("#ff5").apply("deprecated");
        } catch (InvalidHexCode | InvalidRGBAlpha | InvalidRGBValues e) {
            // { @ignore }
        }

        try {
            AnsiChain surroundsColor = new AnsiChain().color("#666");

            prefixTextSurrounds = new String[] {
                surroundsColor.apply("["),
                surroundsColor.apply("]")
            };

            prefixTimeSurrounds = new String[] {
                surroundsColor.apply("["),
                surroundsColor.apply("]")
            };
        } catch (InvalidHexCode | InvalidRGBAlpha | InvalidRGBValues e) {
            // { @ignore }
        }
    }

    /**
     * The time stamp rendering method.
     */
    public Supplier<String> timeStampRenderer = () -> {
        AnsiChain textColor = null;

        try {
            textColor = new AnsiChain().color("#666");
        } catch (InvalidHexCode | InvalidRGBAlpha | InvalidRGBValues e) {
            // { @ignore }
        }

        return textColor != null ? textColor.apply(new SimpleDateFormat("hh:mm:ss aa")
                .format(new Date())
                .replace("a.m.", "AM")
                .replace("p.m.", "PM")
                + new SimpleDateFormat(" yyyy/MM/dd")
                .format(new Date())) : "";
    };
}
