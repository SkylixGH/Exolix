package net.skylix.elixor.elixorFX;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;


public class ElixorFX {
    static int startingX = 0;
    static int startingY = 0;

    private static class RoundedBorder extends AbstractBorder {

        private final Color color;
        private final int gap;

        public RoundedBorder(Color c, int g) {
            color = c;
            gap = g;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(color);
            g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, gap, gap));
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = gap / 2;
            return insets;
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    public static void init() {
        JFrame frame = new JFrame();
        JPanel root = new JPanel();
        JPanel titleBar = new JPanel();

        titleBar.setSize(400, 32);
        titleBar.setBackground(new Color(20, 20, 20));

        titleBar.setMinimumSize(new Dimension(400, 32));
        titleBar.setMaximumSize(new Dimension(400, 32));

        // draggable title bar that moves frame
        startingX = 0;
        startingY = 0;

        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startingX = e.getX();
                startingY = e.getY();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (frame.isResizable()) {
                        if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                            frame.setExtendedState(JFrame.NORMAL);
                        } else {
                            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        }
                    }
                }
            }
        });

        titleBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - startingX, e.getYOnScreen() - startingY);
            }
        });

        titleBar.setBorder(new RoundedBorder(new Color(20, 20, 20), 10));

        // set 10px corner radius
        titleBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        titleBar.setBorder(BorderFactory.createCompoundBorder(titleBar.getBorder(), BorderFactory.createLineBorder(new Color(20, 20, 20), 1)));

        ComponentResizer cr = new ComponentResizer();
        cr.registerComponent(frame);
        cr.setSnapSize(new Dimension(10, 10));
        cr.setMinimumSize(new Dimension(400, 400));

        root.add(titleBar);
        root.setMinimumSize(new Dimension(400, 400));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

        root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // set border radius to 10px
        root.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(20, 20, 20), 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(400, 400);

        root.setSize(400, 400);
        root.setBackground(new Color(32, 32, 32));

        frame.add(root);
        frame.setVisible(true);
    }
}
