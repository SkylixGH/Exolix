package net.skylix.elixor.desktop.system.microsoft.windows;

import com.sun.jna.Structure;

/**
 * Win32 margins structure.
 */
@Structure.FieldOrder({ "cxLeftWidth", "cxRightWidth", "cyTopHeight", "cyBottomHeight" })
public class Margins extends Structure {
    /**
     * Margin left width.
     */
    public int cxLeftWidth;

    /**
     * Margin right width.
     */
    public int cxRightWidth;

    /**
     * Margin top height.
     */
    public int cyTopHeight;

    /**
     * Margin bottom height.
     */
    public int cyBottomHeight;
}
