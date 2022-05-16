package com.github.skylixgh.hello;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class MyApp {
    public static void main(String[] args) {
        JFrame w = new JFrame("GPU Process");

        JPanel gfx = new JPanel() {
            @Override
            public void paintComponent(Graphics g3) {
                Graphics2D g = (Graphics2D) g3;
                
                g.setColor(Color.RED);
                g.setClip(0, 0, 50, 50);
                g.fillRect(0, 0, 100, 100);

                g.setColor(Color.BLACK);
                g.setClip(0, 0, 20, 20);
                g.fillRect(0, 0, 50, 50);
            }
        };

        w.setContentPane(gfx);
        w.setVisible(true);
    }
}
