package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.Desktop;
import net.skylix.elixor.desktop.DesktopFrameType;
import net.skylix.elixor.desktop.DesktopSettings;
import net.skylix.elixor.desktop.animation.AnimationColor;
import net.skylix.elixor.desktop.animation.AnimationInteger;
import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.errors.WindowCannotReinitialize;
import net.skylix.elixor.desktop.errors.WindowNotRunning;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.desktop.ux.uxButton.UXButton;
import net.skylix.elixor.desktop.ux.uxButton.UXButtonSettings;
import net.skylix.elixor.desktop.ux.uxButton.UXButtonType;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxLabel.UXLabel;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanel;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelColumnAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelFlowDirection;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelRowAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelSettings;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.logger.Logger;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MyApp {
    public static class DemoTree {
        public ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
    }

    public static class TreeNode {
        public UXComponent el;

        public TreeNode(UXComponent elt) {
            el = elt;
        }

        public static TreeNode button(String text, Consumer<UXButtonSettings> mod) throws InvalidHexCode {
            UXButtonSettings settings = new UXButtonSettings();
            mod.accept(settings);
            return new TreeNode(new UXButton(text, settings));
        }
    }

    public static void main(String[] args) throws URISyntaxException, WindowAlreadyRunning, InvalidHexCode, WindowCannotReinitialize, IOException {
        UXPanel panel = new UXPanel(new UXPanelSettings() {{
            width = 1000;
            height = 600 - 32;
            rowAlignment = UXPanelRowAlignment.SPACE_BETWEEN;
            columnAlignment = UXPanelColumnAlignment.CENTER;
        }});

        UXComponent pane = new UXComponent();
        JScrollPane scroll = new JScrollPane();

        scroll.setSize(1000, 600 - 32);
        
        for (int i = 0; i < 100; i++) {
            scroll.add(new JLabel("Hello World"));
        }

        pane.setElement(scroll);

        Desktop window = new Desktop(new DesktopSettings() {{
//            frameType = DesktopFrameType.HIDDEN;
            onResize = (win) -> {
                panel.setSize(win.getWidth(), win.getHeight() - 32);
            };
        }});

        InputStream iht = MyApp.class.getClassLoader().getResourceAsStream("hello");
        String ht = iht.toString();

        UXButton button1 = new UXButton(ht);
        UXButton button2 = new UXButton("Button B");
        UXButton button3 = new UXButton("Button C", new UXButtonSettings() {{ type = UXButtonType.HIGHLIGHTED; onMouseClick = (btn) -> {
            Logger.infoBase("Clicked");
            try {
                panel.add(new UXButton("Button D"));
            } catch (InvalidHexCode e) {
                e.printStackTrace();
            }
        }; }});

        UXComponent jbtn = new UXComponent();
        jbtn.setElement(new JButton("Hello World"));

        int pcRam = 293213712;
        UXLabel label = new UXLabel("App Ram Usage: - " + pcRam + "GB");

        UXLabel a = new UXLabel("Hello World");
        UXLabel b = new UXLabel("Hello World");

        UXPanel item1 = new UXPanel(new UXPanelSettings() {{
            cornerRadius = 4;
            width = 300;
            height = 80;
            color = theme.getThemeAttribute("component1");
            rowAlignment = UXPanelRowAlignment.CENTER;
            columnAlignment = UXPanelColumnAlignment.CENTER;
            
            onMouseEnter = (p) -> {
                try {
                    p.setColor(new ThemeDark().getThemeAttribute("highlight4"));
                    a.setColor(new ThemeDark().getThemeAttribute("highlightText4"));
                } catch (InvalidHexCode e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
            
            onMouseExit = (p) -> {
                try {
                    p.setColor(new ThemeDark().getThemeAttribute("component1"));
                    a.setColor(new ThemeDark().getThemeAttribute("text4"));
                } catch (InvalidHexCode e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
        }});

        UXPanel item2 = new UXPanel(new UXPanelSettings() {{
            cornerRadius = 4;
            rowAlignment = UXPanelRowAlignment.CENTER;
            columnAlignment = UXPanelColumnAlignment.CENTER;
            width = 300;
            height = 80;
            color = theme.getThemeAttribute("component1");

            onMouseEnter = (p) -> {
                try {
                    p.setColor(new ThemeDark().getThemeAttribute("highlight4"), 100);
                    b.setColor(new ThemeDark().getThemeAttribute("highlightText4"), 100);
                } catch (InvalidHexCode e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
            
            onMouseExit = (p) -> {
                try {
                    b.setColor(new ThemeDark().getThemeAttribute("text4"), 100);
                    p.setColor(new ThemeDark().getThemeAttribute("component1"), 100);
                } catch (InvalidHexCode e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
        }});
//        panel.add(jbtn);

        UXPanel stuff = new UXPanel(new UXPanelSettings() {{
            width = 300;
            height = (80 * 2) + 10;
            flowDirection = UXPanelFlowDirection.COLUMN;
            spacingY = 10;
        }});

        item1.add(a);
        item2.add(b);

        stuff.add(item1);
        stuff.add(item2);

        panel.add(stuff);

       window.setRootElement(panel);
        window.run();

        // taskbar overlay
        Desktop tb = new Desktop(new DesktopSettings() {{
            frameType = DesktopFrameType.NONE;
            alwaysOnTop = true;
        }});

        UXPanel rootTB = new UXPanel(new UXPanelSettings() {{
            rowAlignment = UXPanelRowAlignment.CENTER;
            columnAlignment = UXPanelColumnAlignment.CENTER;
            width = 1920;
            height = 50;
            color = new ThemeColor("#230500");
        }});

        UXButton btnTB = new UXButton("Stop", new UXButtonSettings() {{
            type = UXButtonType.CRITICAL;
            onMouseClick = (btn) -> {
                try {
                    tb.stop();
                } catch (WindowNotRunning e) {
                    e.printStackTrace();
                }
            };
        }});

        rootTB.add(btnTB);

        tb.setWidth(1920);
        tb.setHeight(50);

        tb.setPositionY(1080 - 50);

        tb.setRootElement(rootTB);
//        tb.run();

        AnimationColor ac = new AnimationColor(new ThemeColor("#000"), (t, c) -> {
            jbtn.getSwingComponent().setBackground(c.getAwtColor());
        });

        jbtn.getSwingComponent().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                try {
                    ac.moveTo(new ThemeColor("#FFAA62"), 0);
                } catch (InvalidHexCode ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                try {
                    ac.moveTo(new ThemeColor("#000"), 1000);
                } catch (InvalidHexCode ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                try {
                    ac.moveTo(new ThemeColor("#FFFFFF"), 1000);
                } catch (InvalidHexCode ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
