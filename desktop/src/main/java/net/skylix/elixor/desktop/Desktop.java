package net.skylix.elixor.desktop;

import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.local.ModJFrame;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanel;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelSettings;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        frame.setSize(1000, 600);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                running = false;
            }
        });

        root = new JPanel();

        JPanel innerFrame = new JPanel();
        JPanel titleBar = new JPanel();
        ThemeColor backgroundColor = settings.theme.getThemeAttribute("layerSolid2");

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
//                switch (type) {
//                    case CLOSE -> {
//                        button = new JButton("X");
//                    }
//
//                    case MINIMIZE -> {
//                        button = new JButton("_");
//                    }
//
//                    case MAXIMIZE -> {
//                        button = new JButton("[]");
//                    }
//                };

                final UXPanel button;
                try {
                    button = new UXPanel(new UXPanelSettings() {{
                        onMouseEnter = (panel) -> {
                            panel.setColor(this.theme.getThemeAttribute("component1"));
                        };

                        onMouseExit = (panel) -> {
                            panel.setColor(new ThemeColor(0, 0, 0, 0));
                        };

                        onMouseClick = (panel) -> {
                            switch (type) {
                                case CLOSE -> {
                                    frame.dispose();
                                }

                                case MINIMIZE -> {
                                    frame.setState(JFrame.ICONIFIED);
                                }

                                case MAXIMIZE -> {
                                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                }
                            }
                        };
                    }});

                    JLabel label = new JLabel();

                    label.setLayout(new BorderLayout());
                    label.setFont(new Font("Arial", Font.PLAIN, 16));
                    label.setForeground(settings.theme.getThemeAttribute("text1").getAwtColor());
                    label.setText("-");
                    label.setPreferredSize(new Dimension(32, 32));
                    label.setBackground(new Color(255, 0, 0));
                    label.setPreferredSize(new Dimension(45, 32));

                    button.add(new UXComponent().setElement(label));
                    button.setSize(45, 32);

                    return button;
                } catch (InvalidHexCode e) {
                    throw new RuntimeException(e);
                }
            };

            JPanel buttons = new JPanel();

            buttons.setLayout(new FlowLayout ( FlowLayout. CENTER, 2, 0 ));
            buttons.setOpaque(false);

            buttons.add(createFrameButton.apply(FrameButtonType.CLOSE).getSwingComponent(), BorderLayout.EAST);
            buttons.add(createFrameButton.apply(FrameButtonType.MINIMIZE).getSwingComponent(), BorderLayout.CENTER);
            buttons.add(createFrameButton.apply(FrameButtonType.MAXIMIZE).getSwingComponent(), BorderLayout.WEST);

            titleBar.add(buttons, BorderLayout.EAST);
        }

        root.setLayout(new GridBagLayout());
        root.setSize(new Dimension(frame.getWidth(), frame.getHeight() - titleBar.getHeight()));
        root.setBackground(backgroundColor.getAwtColor());
        root.setMinimumSize(new Dimension(1000, 600));
        root.setMaximumSize(new Dimension(1000, 600));

        root.setAlignmentY(Component.CENTER_ALIGNMENT);
        root.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("No Root Element Set");

        label.setForeground(settings.theme.getThemeAttribute("text2").getAwtColor());
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        UXComponent defaultComp = new UXComponent(settings.theme, settings.accessibility);
        defaultComp.setElement(label);

        innerFrame.add(titleBar, BorderLayout.NORTH);
        innerFrame.add(root, BorderLayout.CENTER);

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
