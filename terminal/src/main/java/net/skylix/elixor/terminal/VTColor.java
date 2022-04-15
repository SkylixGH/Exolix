package net.skylix.elixor.terminal;

public class VTColor {
    public static void moveRelativeCursor(int x, int y) {
        char escCode = 0x1B;
        int row = 10;
        int column = 10;
        System.out.print("\u001b[1A");
    }
}
