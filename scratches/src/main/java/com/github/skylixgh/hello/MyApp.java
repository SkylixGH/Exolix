package com.github.skylixgh.hello;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.skylix.elixor.desktop.elements.Div;
import net.skylix.elixor.desktop.elements.DivAdapter;
import net.skylix.elixor.desktop.elements.DivMouseEvent;
import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Margin;
import net.skylix.elixor.desktop.window.Window;
import net.skylix.elixor.desktop.window.WindowAdapter;
import net.skylix.elixor.desktop.window.WindowMouseEvent;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyApp {
    public static void main(String[] args) {
        Window win = new Window();
        Div div = new Div();

        JWindow stats = new JWindow();

        JLabel statsMousePos = new JLabel("Mouse Position: ...");
        JLabel statsMouseDown = new JLabel("Mouse Down: ...");
        JLabel statsMouseOver = new JLabel("Mouse Over: ...");

        stats.setLayout(new GridLayout(3, 1));

        stats.add(statsMousePos);
        stats.add(statsMouseDown);
        stats.add(statsMouseOver);

        stats.setSize(200, 100);
        stats.setLocation(30, 30);

        // add 8px corners
        stats.setShape(new RoundRectangle2D.Double(0, 0, 200, 100, 8, 8));
        stats.setAlwaysOnTop(true);

        stats.setVisible(true);

        div.setBorderStrokeWidth(10);

        div.setBorderRadius(new BorderRadius(4));
        div.setMargin(new Margin(10));

        JLabel jsb = new JLabel("J = (Compiled in ...");
        jsb.setFont(new Font("Arial", Font.ITALIC, 20));

        div.getSwingComponent().add(jsb);

        div.addListener(new DivAdapter() {
            @Override 
            public void onMouseEvent(DivMouseEvent e) {
//                System.out.println("Mouse event: X = " + e.getMouseX() + ", Y = " + e.getMouseY() + ", MouseOver = " + e.isMouseOver() + ", MouseDown = " + e.isMouseDown());

                if (e.isMouseOver() && !e.isMouseDown()) {
                    div.setBackgroundColor(new Color(255, 0, 0));
                } else if (e.isMouseOver() && e.isMouseDown()) {
                    div.setBackgroundColor(new Color(0, 0, 255));
                } else {
                    div.setBackgroundColor(new Color(0, 255, 0));
                }

                if (e.isMouseOver()) {
                    statsMouseOver.setText("Mouse Over: true");
                } else {
                    statsMouseOver.setText("Mouse Over: false");
                }

                if (e.isMouseDown()) {
                    statsMouseDown.setText("Mouse Down: true");
                } else {
                    statsMouseDown.setText("Mouse Down: false");
                }

                statsMousePos.setText("Mouse Position: X = " + e.getMouseX() + ", Y = " + e.getMouseY());
            }
        });

        win.addWindowListener(new WindowAdapter() {
            @Override
            public void onMouseEvent(WindowMouseEvent event) {
                System.out.println("Window event: X = " + event.getMouseX() + ", Y = " + event.getMouseY() + ", MouseOver = " + event.isMouseOver());
            }
        });

        win.setRoot(div);
        win.run();

        JFrame setting = new JFrame("Settings");
        JSlider strokeWidth = new JSlider(0, 500, 10);
        JSlider borderRaiud = new JSlider(0, 100, 10);
        JLabel value = new JLabel("0");

        strokeWidth.addChangeListener(new ChangeListener() {{
            
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            // TODO Auto-generated method stub
            div.setBorderStrokeWidth(strokeWidth.getValue());
            value.setText(strokeWidth.getValue() + "");
        }});

        borderRaiud.addChangeListener(new ChangeListener() {
           {

           } 

              @Override
                public void stateChanged(ChangeEvent e) {
                    // TODO Auto-generated method stub
                    div.setBorderRadius(new BorderRadius(0, 0, 0, borderRaiud.getValue()));
                    value.setText(borderRaiud.getValue() + "");
                }

        });

        setting.setLocation(800, 0);

        setting.setLayout(new BorderLayout());
        setting.add(strokeWidth, BorderLayout.NORTH);
        setting.add(borderRaiud, BorderLayout.CENTER);
        setting.add(value, BorderLayout.SOUTH);
        setting.setSize(300, 500);
        setting.setVisible(true);
    }
}
