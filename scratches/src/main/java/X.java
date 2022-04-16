import net.skylix.elixor.terminal.ansiChain.AnsiChain;
import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.ColorNamesGeneric;
import net.skylix.elixor.terminal.color.ColorUtil;
import net.skylix.elixor.terminal.color.ColorsCLI256;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;
import net.skylix.elixor.terminal.logger.Logger;
import net.skylix.elixor.terminal.logger.LoggerSettings;
import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.HashMap;

public class X {
    private static AnsiChain color = new AnsiChain();

    private static Integer item = 0;
    private static HashMap<Integer, Integer> list = new HashMap<>();

    public static void main(String[] args) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        Logger lg = new Logger();

        lg.info("Loading swing window...");


    }

    private static void nextTick() throws InvalidRGBAlpha, InvalidHexCode, InvalidRGBValues {
        item++;

        color.bgColor(
                ColorsCLI256.getColor(item)
        );

        if (ColorUtil.identifyColor(ColorsCLI256.getColor(item)) == ColorNamesGeneric.BLUE) {
            list.put(item, ColorConversion.hexToRGB(ColorsCLI256.getColor(item))[2]);
        }

        if (item < 255) {
            nextTick();
        }
    }

    private static Integer[] sortArr(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }
}
