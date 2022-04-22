import net.skylix.elixor.terminal.logger.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    @DisplayName("Test static loggers")
    public static void baseLoggers() {
        Logger.infoBase("Hello World!");
        Logger.successBase("Hello World!");
        Logger.errorBase("Hello World!");
        Logger.warningBase("Hello World!");
        Logger.debugBase("Hello World!");
        Logger.deprecatedBase("Hello World!");
        Logger.verboseBase("Hello World!");
    }
}
