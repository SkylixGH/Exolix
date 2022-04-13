import net.skylix.elixor.terminal.VTColor;

public class X {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        VTColor.moveRelativeCursor(1, 1);

        System.out.println("Hello, World! 1");
    }
}
