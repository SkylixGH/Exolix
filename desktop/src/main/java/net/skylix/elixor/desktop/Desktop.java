package net.skylix.elixor.desktop;

import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.errors.WindowCannotReinitialize;
import net.skylix.elixor.desktop.errors.WindowNotRunning;
import net.skylix.elixor.desktop.local.ModJFrame;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxLabel.UXLabel;
import net.skylix.elixor.desktop.ux.uxLabel.UXLabelSettings;
import net.skylix.elixor.desktop.ux.uxPanel.*;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Provider;
import java.util.function.Function;

public class Desktop {
    private final ModJFrame frame;
    private final DesktopSettings settings;
    private boolean running = false;
    private final UXPanel root;
    private final UXPanel body;
    private boolean initiallyPowered = false;

    public Desktop(DesktopSettings settings) throws InvalidHexCode {
        this.settings = settings;

        frame = new ModJFrame(
                "Elixor [Single Service]",
                settings.frameType != DesktopFrameType.SYSTEM,
                (settings.frameType == DesktopFrameType.HIDDEN || settings.frameType == DesktopFrameType.NONE) ? 0 : 20
        );

        frame.setAlwaysOnTop(settings.alwaysOnTop);

        if (settings.frameType == DesktopFrameType.NONE) {
            frame.setUndecorated(true);
        }

        frame.setSize(1500, 800);

        root = new UXPanel(new UXPanelSettings() {{
            width = frame.getWidth();
            height = frame.getHeight();
            columnAlignment = UXPanelColumnAlignment.TOP;
            rowAlignment = UXPanelRowAlignment.LEFT;
            flowDirection = UXPanelFlowDirection.COLUMN;
        }});

        body = new UXPanel(new UXPanelSettings() {{
            width = frame.getWidth();
            height = frame.getHeight();
        }});

        renderContent();
    }

    private void renderContent() throws InvalidHexCode {
        final UXPanel titleBar = renderTitleBar();
        final UXPanel defaultRootElement = renderDefaultRootElement();
        final JPanel contentPane = new JPanel();

        contentPane.setLayout(new BorderLayout());
        contentPane.add(root.getSwingComponent());
        contentPane.setBackground(new Color(0, 0, 20));

        body.setColor(settings.theme.getThemeAttribute("layerSolid1"));

        root.add(titleBar);
        root.add(body);
        root.setColor(new ThemeColor("#ff5555"));

        Runnable updateScaling = () -> {
            root.setSize(contentPane.getWidth(), contentPane.getHeight());
            body.setSize(root.getWidth(), root.getHeight() - titleBar.getHeight());

            defaultRootElement.setSize(body.getWidth(), body.getHeight());
            titleBar.setSize(root.getWidth(), titleBar.getHeight());

            // Log all the sizes
            Logger.debugBase("Root: Width: " + root.getWidth() + " Height: " + root.getHeight());
            Logger.debugBase("Body: Width: " + body.getWidth() + " Height: " + body.getHeight());
            Logger.debugBase("Frame: Width: " + frame.getWidth() + " Height: " + frame.getHeight());

            body.getSwingComponent().repaint();
            root.getSwingComponent().repaint();
        };

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateScaling.run();
            }
        });

        frame.setOnMaximizeRunnable(() -> {
            Logger.debugBase("Window was maximized");
            updateScaling.run();
        });

        frame.setOnUnMaximizeRunnable(() -> {
            Logger.debugBase("Window was unmaximized");
            updateScaling.run();
        });

        frame.setContentPane(contentPane);

        setRootElement(defaultRootElement);
    }

    private UXPanel renderDefaultRootElement() throws InvalidHexCode {
        UXPanel defaultRootElement = new UXPanel(new UXPanelSettings() {{
            width = frame.getWidth();
            height = frame.getHeight();
            rowAlignment = UXPanelRowAlignment.CENTER;
            columnAlignment = UXPanelColumnAlignment.CENTER;
            color = settings.theme.getThemeAttribute("layerSolid2");
        }});

        UXLabel message = new UXLabel("Default Root Element", new UXLabelSettings() {{
            color = settings.theme.getThemeAttribute("text1");
        }});

        defaultRootElement.add(message);
        return defaultRootElement;
    }

    private UXPanel renderTitleBar() throws InvalidHexCode {
        if (settings.frameType != DesktopFrameType.GENERIC) {
            return new UXPanel();
        }

        UXPanel element = new UXPanel(new UXPanelSettings() {{
            width = frame.getWidth();
            height = 32;
            color = settings.theme.getThemeAttribute("layerSolid1");
            rowAlignment = UXPanelRowAlignment.SPACE_BETWEEN;
            dragRole = UXPanelWindowDragRole.DRAG;
        }});

        return element;
    }

    public final int getWidth() {
        return frame.getWidth();
    }

    public final int getHeight() {
        return frame.getHeight();
    }

    public final Dimension getSize() {
        return frame.getSize();
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

    public int getPositionX() {
        return frame.getX();
    }

    public int getPositionY() {
        return frame.getY();
    }

    public Point getPosition() {
        return frame.getLocation();
    }

    public final void setAlwaysOnTop(boolean alwaysOnTop) {
        frame.setAlwaysOnTop(alwaysOnTop);
    }

    public final void setRootElement(UXComponent rootElement) {
        body.clear();
        body.add(rootElement);
    }

    public final void run() throws WindowAlreadyRunning, WindowCannotReinitialize {
        if (running || frame.isVisible()) {
            throw new WindowAlreadyRunning("The window is already running");
        }

        if (initiallyPowered) {
            throw new WindowCannotReinitialize("The window cannot be reinitialized after run() was called once");
        }

        initiallyPowered = true;
        running = true;
        frame.setVisible(true);
    }

    public final void stop() throws WindowNotRunning {
        if (!running || !frame.isVisible()) {
            throw new WindowNotRunning("The window is not running");
        }

        running = false;
        frame.setVisible(false);
        frame.dispose();
    }
}
