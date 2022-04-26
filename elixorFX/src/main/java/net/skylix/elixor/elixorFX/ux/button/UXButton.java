package net.skylix.elixor.elixorFX.ux.button;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.StrokeBorder;
import java.awt.*;

public class UXButton extends JPanel {
    public UXButton(String value) {
        super();

        JLabel text = new JLabel(value);

        text.setForeground(new java.awt.Color(255, 255, 255));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // add text centered
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 2;
        gbc.gridheight = 2;

        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.fill = GridBagConstraints.BOTH;

        add(text, gbc);
        setPreferredSize(new Dimension(100, 30));
        setMinimumSize(getSize());

        addMouseListener(new java.awt.event.MouseAdapter() {
             @Override
             public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new java.awt.Color(80, 80, 80));
                repaint();
             }

             @Override
             public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new java.awt.Color(50, 50, 50));
                repaint();
             }
         });

        text.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                setSize(text.getWidth() + 20, 30);
                setMaximumSize(getSize());
            }
        });

        setBackground(new java.awt.Color(50, 50, 50));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setClip(null);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());

        int radius = 6;
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
    }
}
