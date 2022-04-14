import net.skylix.elixor.terminal.VTColor;
import net.skylix.elixor.terminal.color.*;
import net.skylix.elixor.terminal.logger.Logger;
import net.skylix.elixor.terminal.logger.LoggerSettings;

import java.util.Arrays;
import java.util.HashMap;

public class X {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        int inCurrentRow = 0;
        int maxInRow = 10;
        String html = "";

        ColorNamesGeneric col = ColorUtil.identifyColor("#ff5555");
        System.out.println("#50ffab is " + col.name());

        String col256 = ColorUtil.findClosestColor("#ff5555", ColorsCLI256.getAllColors());

        System.out.println("Closest color to #ff0000 is #" + col256);
    }
}
