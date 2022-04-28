package net.skylix.elixor.desktop;

import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.local.ModJFrame;
import net.skylix.elixor.desktop.local.windows.JFrameProcess;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;

public class Desktop {
    private final ModJFrame frame = new ModJFrame();
    private final DesktopSettings settings;
    private boolean running = false;
    private final JPanel root;

    public Desktop(DesktopSettings settings) throws InvalidHexCode {
        this.settings = settings;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        root = new JPanel();

        root.setLayout(new GridBagLayout());
        root.setSize(1000, 600);
        root.setMinimumSize(new Dimension(1000, 600));
        root.setMaximumSize(new Dimension(1000, 600));

        root.setAlignmentY(Component.CENTER_ALIGNMENT);
        root.setAlignmentX(Component.CENTER_ALIGNMENT);

        ThemeColor backgroundColor = settings.theme.getThemeAttribute("layerSolid1");
        root.setBackground(new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha()));

        JLabel label = new JLabel("No Root Element Set");
        label.setFont(label.getFont().deriveFont(48f));

        UXComponent defaultComp = new UXComponent(settings.theme, settings.accessibility);
        defaultComp.setElement(label);

        setRootElement(defaultComp);
        frame.setContentPane(root);
    }

    public Desktop() throws InvalidHexCode {
        this(new DesktopSettings());
    }

    public final void setRootElement(UXComponent rootElement) {
        root.removeAll();
        root.add(rootElement.getSwingComponent());
    }

    public final void run() throws WindowAlreadyRunning {
        if (running || frame.isVisible()) {
            throw new WindowAlreadyRunning("The window is already running");
        }

        running = true;
        frame.setVisible(true);
    }
}
