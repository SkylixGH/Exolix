package com.github.skylixgh.hello;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.skylix.elixor.desktop.elements.Div;
import net.skylix.elixor.desktop.elements.DivAdapter;
import net.skylix.elixor.desktop.elements.DivMouseEvent;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

public class MyApp {
    public static void main(String[] args) {
        Window win = new Window();
        Div div = new Div();

        div.setBorderStrokeWidth(10);
        div.getMargin().setLeft(10);

        JLabel jsb = new JLabel("J = (Compiled in ...");
        jsb.setFont(new Font("Arial", Font.ITALIC, 20));

        div.getSwingComponent().add(jsb);

        div.addListener(new DivAdapter() {
            @Override
            public void onMouseEvent(DivMouseEvent e) {
                System.out.println("Mouse event:");
            }
        });

        win.setRoot(div);
        win.run();

        JFrame setting = new JFrame("Settings");
        JSlider strokeWidth = new JSlider(0, 100, 10);

        strokeWidth.addChangeListener(new ChangeListener() {{
            
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            // TODO Auto-generated method stub
            div.setBorderStrokeWidth(strokeWidth.getValue());
            div.getSwingComponent().repaint();
        }});

        setting.setLocation(800, 0);

        setting.add(strokeWidth);
        setting.setSize(300, 500);
        setting.setVisible(true);
    }
}
