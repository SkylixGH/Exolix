import net.skylix.elixor.terminal.VTColor;
import net.skylix.elixor.terminal.logger.Logger;
import net.skylix.elixor.terminal.logger.LoggerSettings;

public class X {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        VTColor.moveRelativeCursor(1, 1);

        System.out.println("Hello, World! 1");

        Logger customLogger = new Logger(new LoggerSettings() {{
            prefixTextSurrounds = new String[]{"<", ">"};
        }});

        Logger.infoBase("Hello, World! 2");
    }
}
