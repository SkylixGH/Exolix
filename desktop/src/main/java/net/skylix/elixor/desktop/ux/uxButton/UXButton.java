package net.skylix.elixor.desktop.ux.uxButton;

import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;

public class UXButton extends UXComponent {
    private final UXButtonSettings settings;

    public UXButton(String label, UXButtonSettings settings) throws InvalidHexCode {
        super(settings.theme, settings.accessibility);

        this.settings = settings;
        setElement(new Element(this, label));
    }

    public UXButton(String label) throws InvalidHexCode {
        this(label, new UXButtonSettings());
    }

    private class Element extends JPanel {
        private boolean isMouseOver = false;
        private boolean isMouseDown = false;
        private final JLabel label;

        public Element(UXButton button, String value) {
            super();

            label = new JLabel(value);
            label.setFont(label.getFont().deriveFont(Font.PLAIN, 13));

            final Dimension size = new Dimension(
                    label.getPreferredSize().width + 40,
                    30
            );

            System.out.println(size);

            setPreferredSize(size);
            setMinimumSize(size);

            setLayout(new GridBagLayout());

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    isMouseOver = true;
                    repaint();

                    settings.onMouseEnter.accept(button);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    isMouseOver = false;
                    repaint();

                    settings.onMouseExit.accept(button);
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    isMouseDown = true;
                    repaint();

                    settings.onMouseDown.accept(button);
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    isMouseDown = false;
                    repaint();

                    settings.onMouseUp.accept(button);
                }

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    settings.onMouseClick.accept(button);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            final Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(label.getFont());

            final int radius = settings.accessibility.radius1;
            final int labelWidth = label.getPreferredSize().width;
            final int labelHeight = label.getPreferredSize().height;
            final int buttonWidth = labelWidth + 40;
            final int buttonHeight = Math.max(getHeight(), 30);

            final int labelX = (buttonWidth / 2) - (labelWidth / 2);
            final int labelY = (buttonHeight) - (labelHeight / 2);

            setOpaque(false);

            switch (settings.type) {
                case HIGHLIGHTED -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("highlight4").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("highlight3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("highlight2").getAwtColor());
                    } 

                    g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, radius, radius);
                }

                case GENERIC -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("component2").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("component3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("component1").getAwtColor());
                    }

                    g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, radius, radius);
                }

                case OUTLINE -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("component2").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("component3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("component1").getAwtColor());
                    }

                    g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, radius, radius);
                }

                case TEXT -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("component2").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("component3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("component1").getAwtColor());
                    }

                    g2d.fillRoundRect(0, 0, buttonWidth, buttonHeight, radius, radius);
                }

                case CRITICAL -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("critical4").getAwtColor());
                        g2d.drawRoundRect(0, 0, buttonWidth - 1, buttonHeight - 1, radius, radius);
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("critical3").getAwtColor());
                        g2d.fillRoundRect(0, 0, buttonWidth - 1, buttonHeight - 1, radius, radius);
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("critical2").getAwtColor());
                        g2d.fillRoundRect(0, 0, buttonWidth - 1, buttonHeight - 1, radius, radius);
                    } 
                }
            }

            switch (settings.type) {
                case HIGHLIGHTED -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("highlightText4").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("highlightText3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("highlightText2").getAwtColor());
                    }
                }

                case GENERIC -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("text4").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("text3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("text2").getAwtColor());
                    }
                }

                case OUTLINE -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("component2").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("component3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("component1").getAwtColor());
                    }
                }

                case TEXT -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("component2").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("component3").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("component1").getAwtColor());
                    }
                }

                case CRITICAL -> {
                    if ((!isMouseDown && !isMouseOver) || (isMouseDown && !isMouseOver)) {
                        g2d.setColor(theme.getThemeAttribute("critical4").getAwtColor());
                    } else if (isMouseOver && !isMouseDown) {
                        g2d.setColor(theme.getThemeAttribute("criticalText4").getAwtColor());
                    } else if (isMouseOver) {
                        g2d.setColor(theme.getThemeAttribute("criticalText2").getAwtColor());
                    }
                }
            }

            g2d.drawString(label.getText(), labelX, labelY);
            g2d.dispose();
        }
    }
}
