package net.skylix.elixor.desktop.ux.uxComponent;

import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;

public class UXComponent {
    protected JComponent element;
    protected Theme theme;

    public UXComponent(Theme theme) {
        this.theme = theme;
    }

    public UXComponent() throws InvalidHexCode {
        this(new ThemeDark());
    }

    public void setElement(JComponent element) throws InvalidHexCode {
         this.element = element;
    }

    public JComponent getSwingComponent() {
        return element;
    }
}
