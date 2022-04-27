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
        private final JLabel label;

        public Element(String value) {
            super();

            label = new JLabel(value);
            label.setFont(label.getFont().deriveFont(Font.PLAIN, 13));

            final Dimension size = new Dimension(
                    label.getPreferredSize().width + 40,
                    30
            );

            setPreferredSize(size);
            setMinimumSize(size);

            setLayout(new GridBagLayout());

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
            final Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(label.getFont());

            final int radius = 6;
            final int labelWidth = label.getPreferredSize().width;
            final int labelHeight = label.getPreferredSize().height;
            final int buttonWidth = labelWidth + 40;
            final int buttonHeight = Math.max(getHeight(), 30);

            final int labelX = (buttonWidth / 2) - (labelWidth / 2);
            final int labelY = (buttonHeight) - (labelHeight / 2);

            if (!isMouseDown && !isMouseOver) {
                g2d.setColor(theme.getThemeAttribute("component2").getAwtColor());
            } else if (isMouseOver && !isMouseDown) {
                g2d.setColor(theme.getThemeAttribute("component3").getAwtColor());
            } else if (isMouseDown && isMouseOver) {
                g2d.setColor(theme.getThemeAttribute("component1").getAwtColor());
            }

            g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, radius, radius);

            g2d.setColor(theme.getThemeAttribute("text4").getAwtColor());
            g2d.drawString(label.getText(), labelX, labelY);

            g2d.dispose();
        }
    }
}
