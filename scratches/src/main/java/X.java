import net.skylix.elixor.terminal.VTColor;
import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.ColorsCLI256;
import net.skylix.elixor.terminal.logger.Logger;
import net.skylix.elixor.terminal.logger.LoggerSettings;

public class X {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        int inCurrentRow = 0;
        int maxInRow = 10;
        String html = "";

        for (int i = 0; i < 257; i++) {
            String color = ColorsCLI256.getColor(i);

            if (inCurrentRow == maxInRow) {
                html += "<br>";
                inCurrentRow = 0;
            }

            html += "<span style=\"background: " + color + "\">" + i + "</span>";
        }

        System.out.println(html);
    }
}
