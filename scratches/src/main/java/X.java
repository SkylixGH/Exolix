import net.skylix.elixor.apiSocket.APISocket;
import net.skylix.elixor.apiSocket.controller.Controller;
import net.skylix.elixor.apiSocket.controller.request.ControllerRequest;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;
import net.skylix.elixor.terminal.ansiChain.AnsiChain;
import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.ColorNamesGeneric;
import net.skylix.elixor.terminal.color.ColorUtil;
import net.skylix.elixor.terminal.color.ColorsCLI256;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;
import net.skylix.elixor.terminal.logger.Logger;
import java.util.HashMap;

class UwU extends Controller {
    @Override
    public void onActivate(ControllerSocket socket) {
        Logger.infoBase("UwU activated");
    }

    @Override
    public void onDeactivate(ControllerSocket socket) {

    }

    @Override
    public void onRequest(ControllerSocket socket, ControllerRequest request) {

    }
}

public class X {
    private static AnsiChain color = new AnsiChain();

    private static Integer item = 0;
    private static HashMap<Integer, Integer> list = new HashMap<>();

    public static void main() throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        Logger lg = new Logger();

        lg.info("Loading server...");

        APISocket service = new APISocket();
        UwU hello = new UwU();

        service.connectController(hello);
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
