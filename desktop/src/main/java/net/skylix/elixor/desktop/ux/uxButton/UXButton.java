package net.skylix.elixor.desktop.ux.uxButton;

import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;

public class UXButton extends UXComponent {
    public UXButton(String label) throws InvalidHexCode {
        super();
        setElement(new Element(label));
    }

    private class Element extends JPanel {
        private boolean isMouseOver = false;
        private boolean isMouseDown = false;

        public Element(String value) {
            super();

            JLabel text = new JLabel(value);
            text.setForeground(new java.awt.Color(255, 255, 255));

            setLayout(new GridBagLayout());
            add(text);

            Dimension size = new Dimension(100, 30);

            setPreferredSize(size);

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    isMouseOver = true;
                    repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    isMouseOver = false;
                    repaint();
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    isMouseDown = true;
                    repaint();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    isMouseDown = false;
                    repaint();
                }
            });

            text.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    setPreferredSize(size);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setClip(null);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            System.out.println("MouseDown: " + isMouseDown);
            System.out.println("MouseOver: " + isMouseOver);

            if (!isMouseDown && !isMouseOver) {
                g2d.setBackground(theme.getThemeAttribute("dynamic1").getAwtColor());
            } else {
                g2d.setBackground(theme.getThemeAttribute("dynamic2").getAwtColor());
            }

            int radius = 6;
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
    }
}
