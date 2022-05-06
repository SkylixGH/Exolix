package net.skylix.elixor.desktop.ux.uxComponent;

import net.skylix.elixor.desktop.accessibility.Accessibility;
import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;

public class UXComponent {
    protected JComponent element;
    protected Theme theme;
    protected Accessibility accessibility;

    public UXComponent(Theme theme, Accessibility accessibility) {
        this.theme = theme;
        this.accessibility = accessibility;
    }

    public UXComponent() throws InvalidHexCode {
        this(new ThemeDark(), new Accessibility());
    }

    public final UXComponent setElement(JComponent element) {
         this.element = element;
         return this;
    }

    public final UXComponent setElement(UXComponent element) {
        this.element = element.getSwingComponent();
        return this;
    }

    public final JComponent getSwingComponent() {
        return element;
    }
}
