import net.skylix.elixor.terminal.VTColor;
import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.ColorsCLI256;
import net.skylix.elixor.terminal.logger.Logger;
import net.skylix.elixor.terminal.logger.LoggerSettings;

public class X {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        VTColor.moveRelativeCursor(1, 1);

        System.out.println("Hello, World! 1");

        Logger customLogger = new Logger(new LoggerSettings() {static {
        }});

        Integer[] rgb = ColorConversion.hexToRGB(ColorsCLI256.getColor(1) + "ab");

        Logger.infoBase("1 is hex of " + ColorsCLI256.getColor(1));
        customLogger.info("Hello, World! 2" + ColorConversion.rgbToHex(rgb[0], rgb[1], rgb[2], rgb[3]) + " r: " + rgb[0] + " g: " + rgb[1] + " b: " + rgb[2] + " a: " + rgb[3]);
    }
}
