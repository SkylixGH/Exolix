package net.skylix.elixor.desktop.ux.uxButton;

import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UXButton extends UXComponent {
    public UXButton(String label) throws InvalidHexCode {
        super();
        setElement(new Element(label));
    }

    private class Element extends JPanel {
        private boolean isMouseOver = false;
        private boolean isMouseDown = false;
        private JLabel label;

        public Element(String value) {
            super();

            final Dimension size = new Dimension(70, 30);

            label = new JLabel(value);

            label.setFont(label.getFont().deriveFont(13));
            label.setForeground(theme.getThemeAttribute("text1").getAwtColor());

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
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2d = (Graphics2D) g;

            g2d.setClip(null);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            final int radius = 6;
            final int labelWidth = 50;
            final int labelHeight = 30; 
            final int buttonWidth = labelWidth + 20;
            final int buttonHeight = labelHeight;

            final int labelX = (buttonWidth / 2) - (labelHeight / 2);
            final int labelY = (buttonHeight / 2) - (labelHeight / 2) + 20;

            // render label to center of button
            g2d.setColor(theme.getThemeAttribute("text1").getAwtColor());
            g2d.setFont(label.getFont());

            System.out.println(labelWidth + " " + buttonWidth);

            if (!isMouseDown && !isMouseOver) {
                g2d.setColor(theme.getThemeAttribute("component1").getAwtColor());
            } else {
                g2d.setColor(theme.getThemeAttribute("component2").getAwtColor());
            }

            g2d.fillRect(0, 0, buttonWidth, buttonHeight);
            g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, radius, radius);

            g2d.drawString(label.getText(), labelX, labelY);
            g2d.dispose();
        }
    }
}
