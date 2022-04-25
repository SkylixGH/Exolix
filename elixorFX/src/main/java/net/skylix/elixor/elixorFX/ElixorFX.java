package net.skylix.elixor.elixorFX;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import net.skylix.elixor.elixorFX.windows.WindowsJFrameProcess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class ElixorFX {
    /**
     * Extended client area example
     */
    public static void init() {
        ModJFrame frame = new ModJFrame();
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(34, 34, 34));

        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(25, 25, 25));
        titleBar.setPreferredSize(new Dimension(500, 32));
        titleBar.setLayout(new BorderLayout());

        JLabel title = new JLabel("ElixorFX");
        title.setForeground(new Color(255, 255, 255));
        title.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JPanel controls = new JPanel();
        controls.setBackground(new Color(25, 25, 25));
        controls.setLayout(new BorderLayout());
        controls.setPreferredSize(new Dimension(150, 32));

        JButton close = new JButton("X");
        close.setBackground(new Color(25, 25, 25));
        close.setForeground(new Color(255, 255, 255));
        close.setPreferredSize(new Dimension(50, 32));

        JButton minimize = new JButton("-");
        minimize.setBackground(new Color(25, 25, 25));
        minimize.setForeground(new Color(255, 255, 255));
        minimize.setPreferredSize(new Dimension(50, 32));

        JButton maximize = new JButton("o");
        maximize.setBackground(new Color(25, 25, 25));
        maximize.setForeground(new Color(255, 255, 255));
        maximize.setPreferredSize(new Dimension(50, 32));

        JButton helloToolTip = new JButton("Hello ToolTip");
        helloToolTip.setPreferredSize(new Dimension(150, 32));
        helloToolTip.setMaximumSize(new Dimension(150, 32));
        helloToolTip.setLayout(new BorderLayout());

        JWindow toolTip = new JWindow();
        toolTip.setLayout(new BorderLayout());
        toolTip.setBackground(new Color(25, 25, 25));
        toolTip.setForeground(new Color(255, 255, 255));
        toolTip.setSize(new Dimension(150, 32));
        toolTip.setAlwaysOnTop(true);
        toolTip.add(new JLabel("Hello ToolTip"));

        helloToolTip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toolTip.setVisible(true);
                toolTip.setLocation(evt.getLocationOnScreen().x - toolTip.getWidth() - 10, evt.getLocationOnScreen().y - toolTip.getHeight() - 10);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toolTip.setVisible(false);
            }
        });

        helloToolTip.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                toolTip.setLocation(evt.getLocationOnScreen().x + toolTip.getWidth() + 10, evt.getLocationOnScreen().y - toolTip.getHeight() - 10);
            }
        });

        JPanel body = new JPanel();
//        body.setLayout();
        body.add(helloToolTip);
        body.setBackground(new Color(34, 34, 34, 0));

        panel.add(body);

        applyButtonStyle(close);
        applyButtonStyle(minimize);
        applyButtonStyle(maximize);

        controls.add(close, BorderLayout.EAST);
        controls.add(minimize, BorderLayout.WEST);
        controls.add(maximize, BorderLayout.CENTER);

        titleBar.add(controls, BorderLayout.EAST);
        titleBar.add(title, BorderLayout.CENTER);

        panel.add(titleBar, BorderLayout.NORTH);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void applyButtonStyle(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setBackground(new Color(25, 25, 25));
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // dont allow button to be focused
        button.setFocusable(false);

        // on mouse enter
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(45, 45, 45));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(25, 25, 25));
                button.setForeground(new Color(255, 255, 255));
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 65, 65));
                button.setForeground(new Color(0, 0, 0));
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(45, 45, 45));
                button.setForeground(new Color(255, 255, 255));
            }
        });
    }

    public static class ModJFrame extends JFrame {
        final WindowsJFrameProcess process;

        public ModJFrame() {
            super("IT'S A TRAP! (or is it? I don't know yet :P) - ElixorFX");
            process = new WindowsJFrameProcess(true, 32);
        }

        @Override
        public void setVisible(boolean b) {
            super.setVisible(b);
            process.initializeProcess(fetchHWnd());
        }

        private WinDef.HWND fetchHWnd() {
            WinDef.HWND result = new WinDef.HWND();
            result.setPointer(Native.getComponentPointer(this));

            return result;
        }
    }
}
