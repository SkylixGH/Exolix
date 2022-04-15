import net.skylix.elixor.terminal.AnsiChain.AnsiChain;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;

public class X {
    public static void main(String[] args) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        System.out.println("Hello, World!");

        int inCurrentRow = 0;
        int maxInRow = 10;
        String html = "";

        AnsiChain red = new AnsiChain();

        red.color("#ff9955");
        red.bold();

        System.out.println(red.apply("Hey!"));
    }
}
