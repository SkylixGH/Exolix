package net.skylix.elixor.desktop;

import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.local.ModJFrame;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanel;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelColumnAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelRowAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelSettings;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Provider;
import java.util.function.Function;

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
            settings.frameType == DesktopFrameType.HIDDEN ? 0 : 20
        );

        JPanel innerFrame = new JPanel();
        JPanel titleBar = new JPanel();
        ThemeColor backgroundColor = settings.theme.getThemeAttribute("layerSolid2");

        frame.setSize(1000, 600);
        Desktop self = this;

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                running = false;
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                innerFrame.setSize(frame.getWidth(), frame.getHeight());
                root.setSize(frame.getWidth(), frame.getHeight() - titleBar.getHeight());
                settings.onResize.accept(self);
            }
        });

        root = new JPanel();

        innerFrame.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
        innerFrame.setLayout(new BorderLayout());
        innerFrame.setBackground(new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha()));

        if (settings.frameType == DesktopFrameType.GENERIC) {
            titleBar.setPreferredSize(new Dimension(frame.getWidth(), 32));
            titleBar.setBackground(settings.theme.getThemeAttribute("layerSolid1").getAwtColor());
            titleBar.setLayout(new BorderLayout());

            enum FrameButtonType {
                CLOSE,
                MINIMIZE,
                MAXIMIZE
            }

            Function<FrameButtonType, UXComponent> createFrameButton = (FrameButtonType type) -> {
                final UXPanel button;
                final JLabel label = new JLabel();

                label.setFont(new Font("Arial", Font.PLAIN, 13));
                label.setForeground(settings.theme.getThemeAttribute("text4").getAwtColor());

                try {
                    button = new UXPanel(new UXPanelSettings() {{
                        onMouseEnter = (panel) -> {
                            if (type == FrameButtonType.CLOSE) {
                                panel.setColor(this.theme.getThemeAttribute("critical4"));
                                label.setForeground(this.theme.getThemeAttribute("criticalText4").getAwtColor());
                            } else {
                                panel.setColor(this.theme.getThemeAttribute("component1"));
                            }
                        };

                        onMouseExit = (panel) -> {
                            panel.setColor(new ThemeColor(0, 0, 0, 0));
                            label.setForeground(settings.theme.getThemeAttribute("text4").getAwtColor());
                        };

                        onMouseClick = (panel) -> {
                            switch (type) {
                                case CLOSE -> {
                                    frame.dispose();
                                }

                                case MINIMIZE -> {
                                    frame.minimizeNative();
                                }

                                case MAXIMIZE -> {
                                    frame.maximizeNative();
                                }
                            }
                        };

                        rowAlignment = UXPanelRowAlignment.CENTER;
                        columnAlignment = UXPanelColumnAlignment.CENTER;
                    }});

                    switch (type) {
                        case CLOSE -> {
                            label.setText("x");
                        }

                        case MINIMIZE -> {
                            label.setText("-");
                        }

                        case MAXIMIZE -> {
                            label.setText("□");
                        }
                    };

                    button.add(new UXComponent().setElement(label));
                    button.setSize(45, 32);

                    return button;
                } catch (InvalidHexCode e) {
                    throw new RuntimeException(e);
                }
            };

            JPanel buttons = new JPanel();

            buttons.setLayout(new FlowLayout ( FlowLayout. CENTER, 0, 0 ));
            buttons.setOpaque(false);

            buttons.add(createFrameButton.apply(FrameButtonType.MINIMIZE).getSwingComponent());
            buttons.add(createFrameButton.apply(FrameButtonType.MAXIMIZE).getSwingComponent());
            buttons.add(createFrameButton.apply(FrameButtonType.CLOSE).getSwingComponent());

            titleBar.add(buttons, BorderLayout.EAST);
        }

        int titleBarHeight = settings.frameType == DesktopFrameType.GENERIC ? titleBar.getWidth() : 0;

        root.setLayout(null);
        root.setSize(new Dimension(frame.getWidth(), frame.getHeight() - titleBarHeight));
        root.setBackground(backgroundColor.getAwtColor());
        root.setMinimumSize(new Dimension(1000, 600));
        root.setMaximumSize(new Dimension(1000, 600));

        JLabel label = new JLabel("No Root Element Set");

        label.setForeground(settings.theme.getThemeAttribute("text2").getAwtColor());
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        UXComponent defaultComp = new UXComponent(settings.theme, settings.accessibility);
        defaultComp.setElement(label);

        if (settings.frameType == DesktopFrameType.GENERIC)
            innerFrame.add(titleBar, BorderLayout.NORTH);

        innerFrame.add(root, BorderLayout.CENTER);

        setRootElement(defaultComp);
        frame.setContentPane(innerFrame);
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
