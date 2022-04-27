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

            final JLabel text = new JLabel(value);
            final Dimension size = new Dimension(100, 30);

            text.setForeground(new java.awt.Color(255, 255, 255));

            setLayout(new GridBagLayout());
            add(text);
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
            final Graphics2D g2d = (Graphics2D) g;

            g2d.setClip(null);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            if (!isMouseDown && !isMouseOver) {
                g2d.setColor(theme.getThemeAttribute("layerSolid2").getAwtColor());
                System.out.println("Rgba(" + theme.getThemeAttribute("layerSolid2").getRed() + ", " + theme.getThemeAttribute("layerSolid2").getGreen() + ", " + theme.getThemeAttribute("layerSolid2").getBlue() + ", " + theme.getThemeAttribute("layerSolid2").getAlpha() + ")");
            } else {
                g2d.setColor(theme.getThemeAttribute("layerSolid3").getAwtColor());
                System.out.println("Rgba(" + theme.getThemeAttribute("layerSolid2").getRed() + ", " + theme.getThemeAttribute("layerSolid2").getGreen() + ", " + theme.getThemeAttribute("layerSolid2").getBlue() + ", " + theme.getThemeAttribute("layerSolid2").getAlpha() + ")");
            }

            final int radius = 6;
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            g2d.setColor(new Color(0, 0, 0, 0));
        }
    }
}
