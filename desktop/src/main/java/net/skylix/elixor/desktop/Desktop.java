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
    private final ModJFrame frame;
    private final DesktopSettings settings;
    private boolean running = false;
    private final JPanel root;

    public Desktop(DesktopSettings settings) throws InvalidHexCode {
        this.settings = settings;

        frame = new ModJFrame(
            "Elixor [Single Service]", 
            settings.frameType != DesktopFrameType.SYSTEM,
            settings.frameType == DesktopFrameType.HIDDEN ? 0 : 27
        );

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        root = new JPanel();

        JPanel innerFrame = new JPanel();
        JPanel titleBar = new JPanel();
        ThemeColor backgroundColor = settings.theme.getThemeAttribute("layerSolid2");

        innerFrame.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
        innerFrame.setBackground(new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha()));

        if (settings.frameType == DesktopFrameType.GENERIC) {
            titleBar.setLayout(new BorderLayout());
            titleBar.setSize(new Dimension(frame.getWidth(), 27));
            titleBar.setBackground(settings.theme.getThemeAttribute("layerSolid1").getAwtColor());
        }

        root.setLayout(new GridBagLayout());
        root.setSize(1000, 600);
        root.setMinimumSize(new Dimension(1000, 600));
        root.setMaximumSize(new Dimension(1000, 600));
 nk nk  nk n nkn bbnb nb b n n n
        root.setAlignmentY(Component.CENTER_ALIGNMENT);
        root.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("No Root Element Set");
        label.setFont(label.getFont().deriveFont(48f));

        UXComponent defaultComp = new UXComponent(settings.theme, settings.accessibility);
        defaultComp.setElement(label);

        innerFrame.add(titleBar);
        innerFrame.add(defaultComp.getSwingComponent());

        setRootElement(defaultComp);
        frame.setContentPane(innerFrame);
    }

    public Desktop() throws InvalidHexCode {
        this(new DesktopSettings());
    }

    public void setWidth(int width) {
        frame.setSize(width, frame.getHeight());
    }

    public void setHeight(int height) {
        frame.setSize(frame.getWidth(), height);
    }

    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    public void setPositionX(int x) {
        frame.setLocation(x, frame.getY());
    }

    public void setPositionY(int y) {
        frame.setLocation(frame.getX(), y);
    }

    public void setPosition(int x, int y) {
        frame.setLocation(x, y);
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
