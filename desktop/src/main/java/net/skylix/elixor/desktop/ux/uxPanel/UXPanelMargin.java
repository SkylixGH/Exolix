package net.skylix.elixor.desktop.ux.uxPanel;

public class UXPanelMargin {
    private int top;
    private int bottom;
    private int left;
    private int right;

    public UXPanelMargin(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public UXPanelMargin(int topBottom, int leftRight) {
        this(topBottom, topBottom, leftRight, leftRight);
    }

    public UXPanelMargin(int all) {
        this(all, all, all, all);
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTotal() {
        return top + bottom + left + right;
    }
}
