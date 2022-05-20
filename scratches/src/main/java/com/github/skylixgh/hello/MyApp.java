package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.component.WindowDragState;
import net.skylix.elixor.desktop.component.div.Div;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.engines.Layout;
import net.skylix.elixor.desktop.engines.layout.BorderLayout;
import net.skylix.elixor.desktop.system.microsoft.windowsUtil.MicrosoftWindowsController;
import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class MyApp {
    private static MicrosoftWindowsController wc = null;
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        final Window window = new Window("Elixor");
 
        configureWindow(window);

        final Div div = new Div();

        div.disableEdgeClipping();

        div.setSize(new Size(window.getWidth() - 16, window.getHeight() - 39));

        final Div titleBar = new Div();

        titleBar.setSize(new Size(window.getWidth(), 32));
        titleBar.setPosition(new Position(0, 0));
        titleBar.setBackgroundColor(new Color(12, 12, 12));

        final Div closeButton = new Div();

        titleBar.add(closeButton);
        titleBar.disableEdgeClipping();

        closeButton.setPosition(new Position(
            window.getWidth() - 45,
            0
        ));

        final ArrayList<Div> bl = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            final Div div2 = new Div();

            div2.setSize(new Size(i / 4, 10));
            div2.setBackgroundColor(new Color(83, 150, 255));

            div.add(div2);
            bl.add(div2);
        }

        closeButton.setWindowDragState(WindowDragState.ALLOW);

        div.setPosition(new Position(20, 20));
        new Thread(() -> {
            while (true) { 
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    // Ignore 
                }

                if (closeButton.isMouseOver()) {
                    closeButton.setBackgroundColor(new Color(255, 83, 24));
                } else {
                    closeButton.setBackgroundColor(new Color(0, 0, 0));
                }

                for (Div el : bl) {
                    if (el.isMouseOver()) {
                        el.setBackgroundColor(new Color(255, 84, 20));
                    } else {
                        el.setBackgroundColor(new Color(83, 150, 255));
                    }
                }
            }
        }).start();

        closeButton.setSize(new Size(45, 32));

        closeButton.setBackgroundColor(new Color(255, 83, 20));

        window.add(titleBar); 

        window.add(div);
        window.run();

        wc = window.getOSWindowsController();
    }

    private static void configureWindow(Window win) {
        win.setSize(new Size(800, 500));
    }
}
 