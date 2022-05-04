package com.github.skylixgh.hello;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import net.skylix.elixor.desktop.Desktop;
import net.skylix.elixor.desktop.DesktopFrameType;
import net.skylix.elixor.desktop.DesktopSettings;
import net.skylix.elixor.desktop.animation.AnimationPercent;
import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.desktop.ux.uxButton.UXButton;
import net.skylix.elixor.desktop.ux.uxButton.UXButtonSettings;
import net.skylix.elixor.desktop.ux.uxButton.UXButtonType;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanel;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelColumnAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelRowAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelSettings;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static void main(String[] args) throws URISyntaxException, WindowAlreadyRunning, InvalidHexCode {
        UXPanel panel = new UXPanel(new UXPanelSettings() {{
            width = 1000;
            height = 600 - 32;
            rowAlignment = UXPanelRowAlignment.CENTER;
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
        UXButton button1 = new UXButton("Button A");
        UXButton button2 = new UXButton("Button B");
        UXButton button3 = new UXButton("Button C", new UXButtonSettings() {{ type = UXButtonType.HIGHLIGHTED; }});

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(pane);

        window.setRootElement(panel);
//        window.run();

        AnimationPercent an = new AnimationPercent(0, (ann, i) -> {
            System.out.println(i);

            if (i > 50) {
                ann.transitionTo(0, 1, 100);
            }
        });

        an.transitionTo(100, 1, 100);
    }
}
